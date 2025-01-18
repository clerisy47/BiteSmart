import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.bitesmart.navigation.NavigationScreens
import com.app.bitesmart.viewModels.UserViewModel
import com.app.bitesmart.viewModels.UserViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AllergiesScreen(
    navController: NavController
) {
    val userViewModel: UserViewModel = viewModel(
        factory = UserViewModelFactory(context = navController.context)
    )
    var allergyInput by remember { mutableStateOf("") }
    var allergies by remember { mutableStateOf(listOf<String>()) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    // Load the user's allergies on screen launch
    LaunchedEffect(Unit) {
        scope.launch {
            allergies = userViewModel.getAllergies()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text("Add Health Conditions", style = MaterialTheme.typography.headlineMedium)
        Text("Such as diabetes, Peanuts(allergic to), .....")
        Spacer(modifier = Modifier.padding(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            BasicTextField(
                value = allergyInput,
                onValueChange = {allergyInput = it},
                modifier = Modifier.weight(1f)
//                    .height(56.dp)
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(8.dp)),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                textStyle = MaterialTheme.typography.bodyLarge.copy(lineHeight = 24.sp)
            )

            Button(onClick = {
                if (allergyInput.isNotEmpty()) {
                    allergies = allergies + allergyInput
                    allergyInput = ""
                }
            }) {
                Text("Add")
            }
        }

        Button(
            onClick = {
                scope.launch {
                    userViewModel.saveAllergies(allergies)
                    Toast.makeText(context, "Wait data is being updated",Toast.LENGTH_SHORT).show()
                    delay(2000)
                    navController.navigate(route = NavigationScreens.UserDashboardScreen.name)
                    userViewModel.setAllergiesCompleted()
                }
            },

        ) {
            Text("Save Allergies")
        }
        Button(
            onClick = {
                scope.launch {
                    navController.navigate(route = NavigationScreens.UserDashboardScreen.name)
                    userViewModel.setAllergiesCompleted()
                }
            },

            ) {
            Text("Skip")
        }

        Text("Your Allergies:", style = MaterialTheme.typography.titleMedium)
        allergies.forEach { allergy ->
            Text("• $allergy", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AllergiesPreview(){
    val navController = rememberNavController()
    AllergiesScreen(navController)
}


