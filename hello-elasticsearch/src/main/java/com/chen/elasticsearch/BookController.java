package com.chen.elasticsearch;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * BookController
 *
 * @Author LeifChen
 * @Date 2018-08-21
 */
@RestController
@RequestMapping("/book/novel")
public class BookController {

    @Resource
    private TransportClient client;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 新增
     *
     * @param title
     * @param author
     * @param wordCount
     * @param publishDate
     * @return
     */
    @PostMapping
    public ResponseEntity<String> add(@RequestParam(name = "title") String title,
                                      @RequestParam(name = "author") String author,
                                      @RequestParam(name = "word_count") int wordCount,
                                      @RequestParam(name = "publish_date")
                                      @DateTimeFormat(pattern = "yyyy-MM-dd") Date publishDate) {
        try {
            XContentBuilder content = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("title", title)
                    .field("author", author)
                    .field("word_count", wordCount)
                    .field("publish_date", publishDate.getTime())
                    .endObject();
            IndexResponse result = client.prepareIndex("book", "novel")
                    .setSource(content)
                    .get();
            return new ResponseEntity<>(result.getId(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 查询
     *
     * @param id
     * @return
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> get(@RequestParam(name = "id") String id) {
        if (id.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        GetResponse result = client.prepareGet("book", "novel", id).get();
        if (!result.isExists()) {
            return new ResponseEntity<>((HttpStatus.NOT_FOUND));
        }

        return new ResponseEntity<>(result.getSource(), HttpStatus.OK);
    }

    /**
     * 更新
     *
     * @param id
     * @param title
     * @param author
     * @return
     */
    @PutMapping
    public ResponseEntity<String> update(@RequestParam(name = "id") String id,
                                         @RequestParam(name = "title", required = false) String title,
                                         @RequestParam(name = "author", required = false) String author) {
        UpdateRequest update = new UpdateRequest("book", "novel", id);

        try {
            XContentBuilder builder = XContentFactory.jsonBuilder().startObject();
            if (title != null) {
                builder.field("title", title);
            }
            if (author != null) {
                builder.field("author", author);
            }
            builder.endObject();
            update.doc(builder);

            UpdateResponse result = client.update(update).get();
            return new ResponseEntity<>(result.getResult().toString(), HttpStatus.OK);
        } catch (IOException | ExecutionException | InterruptedException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam(name = "id") String id) {
        DeleteResponse result = client.prepareDelete("book", "novel", id).get();
        return new ResponseEntity<>(result.getResult().toString(), HttpStatus.OK);
    }

    @GetMapping("/query")
    public ResponseEntity query(@RequestParam(name = "author", required = false) String author,
                                @RequestParam(name = "title", required = false) String title,
                                @RequestParam(name = "gt_word_count", defaultValue = "0") int gtWordCount,
                                @RequestParam(name = "lt_word_count", required = false) Integer ltWordCount) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if (author != null) {
            boolQuery.must(QueryBuilders.matchQuery("author", author));
        }
        if (title != null) {
            boolQuery.must(QueryBuilders.matchQuery("title", title));
        }

        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("word_count").from(gtWordCount);
        if (ltWordCount != null && ltWordCount > 0) {
            rangeQuery.to(ltWordCount);
        }

        SearchRequestBuilder builder = client.prepareSearch("book")
                .setTypes("novel")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(boolQuery)
                .setQuery(rangeQuery)
                .setFrom(0)
                .setSize(10);
        System.out.println(builder);

        SearchResponse response = builder.get();
        List<Map<String, Object>> result = new ArrayList<>();
        for (SearchHit hit : response.getHits()) {
            result.add(hit.getSource());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
