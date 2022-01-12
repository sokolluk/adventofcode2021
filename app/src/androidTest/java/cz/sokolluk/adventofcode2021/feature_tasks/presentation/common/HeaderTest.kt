package cz.sokolluk.adventofcode2021.feature_tasks.presentation.common

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class RGLimitHeaderTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `will display text correctly`() {
        val text = "test"
        composeTestRule.setContent {
            Header(text) { }
        }

        composeTestRule
            .onNodeWithText(text)
            .assertIsDisplayed()
    }
}
