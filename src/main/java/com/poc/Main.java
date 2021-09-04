/*
 * Copyright (c) 2019 - Papp Bence. All Rights Reserved.
 */
package com.poc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.TreeMap;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Main {

  public static void main(String[] args) throws IOException {
    Path filePath = Path.of("src/main/resources/input.json");
    String inputJson = Files.readString(filePath);

//    FileReader inputJson = new FileReader("src/main/resources/input.json");   //Can't ready it twice out of the box

    JsonMapper configuredMapper = JsonMapper.builder().nodeFactory(new SortingNodeFactory()).build();
    ObjectMapper defaultMapper = new ObjectMapper();

    JsonNode configuredJsonNode = configuredMapper.readTree(inputJson);

    System.out.println("Bare inputJson: " + inputJson);
    System.out.println("ConfiguredMapper output: " + configuredMapper.writeValueAsString(configuredJsonNode));

    JsonNode defaultJsonNode = defaultMapper.readTree(inputJson);
    System.out.println("Default Mapper output: " + defaultMapper.writeValueAsString(defaultJsonNode));

    JsonNode customMappedJsonNode = configuredMapper.valueToTree(defaultJsonNode);
    System.out.println("Parsed with Default Mapper, Re-serialized with ConfiguredMapper: " + defaultMapper
        .writeValueAsString(customMappedJsonNode));
  }

  static class SortingNodeFactory extends JsonNodeFactory {
    @Override
    public ObjectNode objectNode() {
      return new ObjectNode(this, new TreeMap<String, JsonNode>());
    }
  }
}
