package com.example.businesscard.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.businesscard.viewmodel.BusinessCardViewModel
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import com.example.businesscard.R


@Composable
fun MainScreen(viewModel: BusinessCardViewModel = viewModel()) {
    val user by viewModel.userData.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp, start = 24.dp, end = 24.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Business Card", style = MaterialTheme.typography.headlineLarge)

        // Preview
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.avatar),
                        contentDescription = "Avatar",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(user.name, style = MaterialTheme.typography.titleLarge)
                        Text(user.profession)
                        Text("📞 ${user.phone}")
                        Text("🛠️ Skills: ${user.skills}")
                        Text("🐙 GitHub: ${user.github}")
                        Text("🔗 LinkedIn: ${user.linkedin}")
                    }
                }
            }
        }

        // Editable fields
        OutlinedTextField(
            value = user.name,
            onValueChange = viewModel::updateName,
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = user.profession,
            onValueChange = viewModel::updateProfession,
            label = { Text("Profesión") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = user.phone,
            onValueChange = viewModel::updatePhone,
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = user.skills,
            onValueChange = viewModel::updateSkills,
            label = { Text("Skills (separados por coma)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = user.github,
            onValueChange = viewModel::updateGithub,
            label = { Text("GitHub") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = user.linkedin,
            onValueChange = viewModel::updateLinkedIn,
            label = { Text("LinkedIn") },
            modifier = Modifier.fillMaxWidth()
        )

        val context = LocalContext.current

        Button(
            onClick = {
                viewModel.saveAll()
                Toast.makeText(context, "Datos guardados", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }
    }
}

