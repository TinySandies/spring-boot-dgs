package org.spica.springbootdgs.datafetchers

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import org.spica.springbootdgs.types.Show

/**
 *
 * @author ext.chenchundeng5
 * Created on 2022/7/22
 */
@DgsComponent
class ShowsDataFetcher {

    private val shows = listOf(
        Show("Stranger Things", 2016),
        Show("Ozark", 2017),
        Show("The Crown", 2016),
        Show("Dead to Me", 2019),
        Show("Orange is the New Black", 2013)
    )

    @DgsQuery
    fun shows(@InputArgument title: String?) =
        if (title != null) shows.filter { it.title.contains(title) } else shows
}