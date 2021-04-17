package org.nkilleen.mediafinder.controllers;

import java.io.IOException;
import java.net.URI;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.nkilleen.mediafinder.models.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MediumController {
   public static final String MEDIUM = "medium";
   public static final String BASE_PATH = MEDIUM;

   @Autowired private ObjectMapper objectMapper;
   @Autowired private RestHighLevelClient restHighLevelClient;

   @PostMapping(path = BASE_PATH, consumes = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<?> createMedia(@RequestBody Media media) throws IOException {
      IndexRequest indexRequest = new IndexRequest(MEDIUM);
      indexRequest.source(objectMapper.writeValueAsBytes(media), XContentType.JSON);
      String mediaId = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT).getId();
      return ResponseEntity.created(URI.create(BASE_PATH + "/" + mediaId)).build();
   }

   @GetMapping(path = BASE_PATH + "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
   public String getMedium(@PathVariable String id) throws IOException {
      return restHighLevelClient.get(new GetRequest(MEDIUM, id), RequestOptions.DEFAULT).getSourceAsString();
   }

}
