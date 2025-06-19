package com.forth_npcs.npcsrewrite.controller;
import com.forth_npcs.npcsrewrite.model.RewriteClass;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class RewriteServiceController2 {

    @PostMapping("/rewriteservices")
    public ResponseEntity<String> handleSubmit(@RequestBody RewriteClass requestBody) {
        System.out.println("Received input:");
        System.out.println(requestBody.getInput1());
        System.out.println(requestBody.getInput2());
        System.out.println(requestBody.getInput3());

        try {
            String results = JarExecutor.execute("E:\\NPCS_services\\npcs-rewrite\\npcs-rewrite\\src\\main\\java\\com\\forth_npcs\\npcsrewrite\\jarfiles\\serviceSparqlStarUpdated.jar",
                    List.of(requestBody.getInput1(), requestBody.getInput2(), requestBody.getInput3())
             /*       List.of("Service_SPARQL_Star","query","PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                            "PREFIX bsbm-inst: <http://www4.wiwiss.fu-berlin.de/bizer/bsbm/v01/instances/>\n" +
                            "PREFIX bsbm: <http://www4.wiwiss.fu-berlin.de/bizer/bsbm/v01/vocabulary/>\n" +
                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                            "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                            "SELECT DISTINCT ?product ?label WHERE {\n" +
                            "    VALUES ( ?bgp1 ) { ( <http://localhost:34205/sparql/?default-graph-uri=http://www.ratingsite0.fr/> ) }\n" +
                            "    SERVICE ?bgp1 {   \n" +
                            "        ?product rdfs:label ?label .\n" +
                            "        # const bsbm:ProductType647\n" +
                            "        ?product rdf:type ?localProductType .\n" +
                            "        ?localProductType owl:sameAs bsbm:ProductType647 .\n" +
                            "        # const bsbm:ProductFeature8774\n" +
                            "        ?product bsbm:productFeature ?localProductFeature1 . \n" +
                            "        ?localProductFeature1 owl:sameAs bsbm:ProductFeature8774 .\n" +
                            "        # const bsbm:ProductFeature16935\n" +
                            "        ?product bsbm:productFeature ?localProductFeature2 . \n" +
                            "        ?localProductFeature2 owl:sameAs bsbm:ProductFeature16935 .\n" +
                            "        ?product bsbm:productPropertyNumeric1 ?value1 . \n" +
                            "                # const \"744\"^^xsd:integer < ?value1\n" +
                            "        FILTER (?value1 > 744) \n" +
                            "    }\n" +
                            "}\n" +
                            "ORDER BY ?product ?label\n" +
                            "LIMIT 10") */
            );
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}

class JarExecutor {
    public static String execute(String jarFilePath, List<String> args) throws IOException, InterruptedException {
        List<String> command = new ArrayList<>();
        command.add("java");
        command.add("-jar");
        command.add(jarFilePath);
        command.addAll(args);

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder outputBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^");
                System.out.println(line);

                outputBuilder.append(line);
            }
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("JAR execution failed with exit code: " + exitCode);
            }
            return outputBuilder.toString();
        }
    }
}
