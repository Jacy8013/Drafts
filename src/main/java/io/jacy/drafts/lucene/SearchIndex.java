package io.jacy.drafts.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author Jacy
 */
public class SearchIndex {
    public static void main(String[] args) throws IOException, ParseException {
        Analyzer analyzer = new StandardAnalyzer();
        Directory directory = new NIOFSDirectory(Paths.get("index"));

        QueryParser queryParser = new QueryParser("name", analyzer);
        Query query = queryParser.parse("lucene");
        IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(directory));

        ScoreDoc[] docs = indexSearcher.search(query, 1000).scoreDocs;
        for (int i = 0; i < docs.length; i++) {
            Document hitDoc = indexSearcher.doc(docs[i].doc);
            System.out.println(hitDoc);
        }
        indexSearcher.getIndexReader().close();
        directory.close();
    }
}
