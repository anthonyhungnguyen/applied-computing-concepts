package ca.uwindsor.appliedcomputing.final_project.controller;

import ca.uwindsor.appliedcomputing.final_project.dto.WebCrawlerData;
import ca.uwindsor.appliedcomputing.final_project.service.WebCrawlerService;
import ca.uwindsor.appliedcomputing.final_project.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The WebCrawlerController class provides a REST API endpoint to crawl a web URL and retrieve its contents.
 */
@RestController
@RequestMapping("/api/web-crawler")
@Slf4j
public class WebCrawlerController {
    @Autowired
    private WebCrawlerService webCrawlerService;

    /**
     * Handles GET requests to crawl a web URL and retrieve its contents.
     * This method accepts a URL as a request parameter, uses the WebCrawlerService to crawl the URL,
     * and returns the crawled data as a WebCrawlerData object.
     *
     * @param url the web URL to be crawled
     * @return a WebCrawlerData object containing the URL, time, and HTML contents
     */
    @GetMapping
    public ResponseEntity<WebCrawlerData> getContentsFromWebUrl(@RequestParam("url") String url) {
        if (!ValidatorUtil.isValidHtmlUrl(url)) {
            log.error("URL is empty or null.");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(webCrawlerService.crawlWebUrl(url));
    }
}
