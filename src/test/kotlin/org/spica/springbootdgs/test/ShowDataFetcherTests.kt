package org.spica.springbootdgs.test

import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest
import org.junit.jupiter.api.Test
import org.spica.springbootdgs.client.ShowsGraphQLQuery
import org.spica.springbootdgs.client.ShowsProjectionRoot
import org.spica.springbootdgs.datafetchers.ShowsDataFetcher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 *
 * @author ext.chenchundeng5
 * Created on 2022/7/22
 */
@SpringBootTest(classes = [DgsAutoConfiguration::class, ShowsDataFetcher::class])
class ShowDataFetcherTests {

    @Autowired
    lateinit var executor: DgsQueryExecutor

    @Test
    fun test() {
        val query = GraphQLQueryRequest(
            ShowsGraphQLQuery.newRequest().title("Ozark").build(),
            ShowsProjectionRoot().title()
        )

        val titles: List<String> = executor.executeAndExtractJsonPath(
            query.serialize(), "data.shows[*].title")

        assert(titles.contains("Ozark")) {
            "The result dose not contain Ozark"
        }
    }
}