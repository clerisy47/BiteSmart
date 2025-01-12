package  com.app.bitesmart.screens.allergies
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.bitesmart.viewModels.UserAllergiesViewModel

@Composable
fun AddAllergyScreen(
    userAllergiesViewModel: UserAllergiesViewModel = viewModel()
) {
    // Observe user allergies from the ViewModel
    val allergies = userAllergiesViewModel.userAllergies.value
    val allergyInput = userAllergiesViewModel.allergyInput.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Display current allergies
        Text(text = "Your Allergies: ${allergies.joinToString(", ")}", style = MaterialTheme.typography.bodyMedium)

        // Input field to add allergy
        TextField(
            value = allergyInput,
            onValueChange = { userAllergiesViewModel.allergyInput.value = it },
            label = { Text("Enter Allergy") },
            modifier = Modifier.fillMaxWidth()
        )

        // Button to add allergy
        Button(
            onClick = { userAllergiesViewModel.addAllergy() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Allergy")
        }
    }

    // Initial fetch of allergies
    LaunchedEffect(key1 = Unit) {
        userAllergiesViewModel.fetchUserAllergies()
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AddAllergyScreen()
}
