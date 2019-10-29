package ru.tinkoff.sample_eba.search

object SomeDataToSomeUi : (SearchResult) -> SearchResultUi {
    override fun invoke(data: SearchResult): SearchResultUi = SearchResultUi(data.text, data.text)
}