package nl.avisi.structurizr.site.generatr.site.model

import assertk.assertThat
import assertk.assertions.containsAll
import assertk.assertions.containsAtLeast
import kotlin.test.Test

class TableViewModelTest : ViewModelTest() {
    private val pageViewModel = object : PageViewModel(generatorContext()) {
        override val url = "/test-page"
        override val pageSubTitle = "Test page"
    }

    @Test
    fun `header rows`() {
        val viewModel = TableViewModel.create {
            headerRow(headerCell("1"), headerCell("2"), headerCell("3"))
        }

        assertThat(viewModel.headerRows).containsAtLeast(
            TableViewModel.RowViewModel(
                listOf(
                    TableViewModel.TextCellViewModel("1", isHeader = true),
                    TableViewModel.TextCellViewModel("2", isHeader = true),
                    TableViewModel.TextCellViewModel("3", isHeader = true),
                )
            )
        )
    }

    @Test
    fun `header cell with grey text`() {
        val viewModel = TableViewModel.create {
            headerRow(headerCell("1", greyText = true))
        }

        assertThat(viewModel.headerRows).containsAtLeast(
            TableViewModel.RowViewModel(
                listOf(
                    TableViewModel.TextCellViewModel("1", isHeader = true, greyText = true),
                )
            )
        )
    }

    @Test
    fun `body rows`() {
        val viewModel = TableViewModel.create {
            bodyRow(cell("1"), cell("2"), cell("3"))
        }

        assertThat(viewModel.bodyRows).containsAtLeast(
            TableViewModel.RowViewModel(
                listOf(
                    TableViewModel.TextCellViewModel("1", isHeader = false),
                    TableViewModel.TextCellViewModel("2", isHeader = false),
                    TableViewModel.TextCellViewModel("3", isHeader = false),
                )
            )
        )
    }

    @Test
    fun `cell with link`() {
        val viewModel = TableViewModel.create {
            bodyRow(cellWithLink(pageViewModel, "click me", "/decisions"))
        }

        assertThat(viewModel.bodyRows).containsAtLeast(
            TableViewModel.RowViewModel(
                listOf(
                    TableViewModel.LinkCellViewModel(
                        LinkViewModel(pageViewModel, "click me", "/decisions"),
                        isHeader = false
                    )
                )
            )
        )
    }

    @Test
    fun `header cell with link`() {
        val viewModel = TableViewModel.create {
            bodyRow(headerCellWithLink(pageViewModel, "click me", "/decisions"))
        }

        assertThat(viewModel.bodyRows).containsAtLeast(
            TableViewModel.RowViewModel(
                listOf(
                    TableViewModel.LinkCellViewModel(
                        LinkViewModel(pageViewModel, "click me", "/decisions"),
                        isHeader = true
                    )
                )
            )
        )
    }

    @Test
    fun `cell with external link`() {
        val viewModel = TableViewModel.create {
            bodyRow(cellWithExternalLink("Temporary URI", "https://tempuri.org/"))
        }

        assertThat(viewModel.bodyRows).containsAtLeast(
            TableViewModel.RowViewModel(
                listOf(
                    TableViewModel.ExternalLinkCellViewModel(
                        ExternalLinkViewModel("Temporary URI", "https://tempuri.org/"),
                        isHeader = false
                    )
                )
            )
        )
    }

    @Test
    fun `cell with index`() {
        val viewModel = TableViewModel.create {
            bodyRow(cellWithIndex("1"))
        }

        assertThat(viewModel.bodyRows).containsAtLeast(
            TableViewModel.RowViewModel(
                listOf(
                    TableViewModel.TextCellViewModel(
                        "1",
                        isHeader = false,
                        greyText = false,
                        boldText = true,
                        oneTenthWidth = true
                    )
                )
            )
        )
    }
}
