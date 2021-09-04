/*
 * Copyright (c) 2019 - Papp Bence. All Rights Reserved.
 */
package com.poc;

import java.util.TreeMap;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Main {

  public static void main(String[] args) throws JsonProcessingException {
    final String input =
        "{\n" + "\t\"name\": \"Bence\",\n" + "\t\"age\": 32,\n" + "\t\"alpha\": \"alpha\",\n" + "\t\"beta\": \"beta\"\n"
            + "}";

    JsonMapper mapper = JsonMapper.builder().nodeFactory(new SortingNodeFactory()).build();

    JsonNode node = mapper.readTree(input);
    String output = mapper.writeValueAsString(node);

    System.out.println("Bare input: " + input);
    System.out.println("With Property sorting: " + output);
  }

  static class SortingNodeFactory extends JsonNodeFactory {
    @Override
    public ObjectNode objectNode() {
      return new ObjectNode(this, new TreeMap<String, JsonNode>());
    }
  }
}
