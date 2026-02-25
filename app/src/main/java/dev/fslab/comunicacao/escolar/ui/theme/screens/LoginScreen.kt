package dev.fslab.comunicacao.escolar.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.fslab.comunicacao.escolar.ui.theme.ComunicacaoEscolarTheme
import dev.fslab.comunicacao.escolar.ui.theme.LocalComunicacaoEscolarColors

/**
 * LoginScreen - Tela de autenticação (login) da aplicação
 * A tela de login ocupa 100% da altura da tela.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = false,
    onToggleTheme: () -> Unit = {},
    onEsqueciSenha: (String) -> Unit = {},
    onRegister: () -> Unit = {},
    onLogin: () -> Unit = {}
) {
    val colors = LocalComunicacaoEscolarColors.current

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var senhaVisivel by remember { mutableStateOf(false) }
    var lembrarMe by remember { mutableStateOf(false) }

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            colors.primaryDark,
            colors.primary,
            colors.primary.copy(alpha = 0.7f)
        )
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(gradientBrush)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Barra superior com botão de tema
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                // Botão alternar tema
                IconButton(onClick = onToggleTheme) {
                    Icon(
                        imageVector = if (isDarkTheme) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                        contentDescription = if (isDarkTheme) "Modo claro" else "Modo escuro",
                        tint = colors.textOnPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Ícone do app
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(colors.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Ícone usuários",
                    tint = colors.textOnPrimary,
                    modifier = Modifier.size(50.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Título
            Text(
                text = "Fila Cidadã",
                color = colors.textOnPrimary,
                style = MaterialTheme.typography.displaySmall
            )

            // Subtítulo
            Text(
                text = "Acesse sua conta",
                color = colors.textOnPrimary.copy(alpha = 0.8f),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Card branco com formulário
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = colors.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    // Campo Email
                    Text(
                        text = "Email",
                        style = MaterialTheme.typography.titleMedium,
                        color = colors.textPrimary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = {
                            Text("seu@email.com", color = colors.mediumGray)
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Filled.Email,
                                contentDescription = "Email",
                                tint = colors.primary
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = colors.inputBorder,
                            focusedBorderColor = colors.primary,
                            cursorColor = colors.primary,
                            focusedTextColor = colors.textInput,
                            unfocusedTextColor = colors.textInput
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Campo Senha
                    Text(
                        text = "Senha",
                        style = MaterialTheme.typography.titleMedium,
                        color = colors.textPrimary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = senha,
                        onValueChange = { senha = it },
                        placeholder = {
                            Text("••••••••", color = colors.mediumGray)
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Filled.Lock,
                                contentDescription = "Senha",
                                tint = colors.primary
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { senhaVisivel = !senhaVisivel }) {
                                Icon(
                                    imageVector = if (senhaVisivel) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                    contentDescription = if (senhaVisivel) "Ocultar senha" else "Mostrar senha",
                                    tint = colors.iconGray
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        visualTransformation = if (senhaVisivel) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = colors.inputBorder,
                            focusedBorderColor = colors.primary,
                            cursorColor = colors.primary,
                            focusedTextColor = colors.textInput,
                            unfocusedTextColor = colors.textInput
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Lembrar-me e Esqueci a senha
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = lembrarMe,
                                onCheckedChange = { lembrarMe = it },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = colors.primary
                                )
                            )
                            Text(
                                text = "Lembrar-me",
                                style = MaterialTheme.typography.labelMedium,
                                color = colors.textSecondary
                            )
                        }
                        Text(
                            text = "Esqueci a senha",
                            style = MaterialTheme.typography.labelMedium,
                            color = colors.primary,
                            modifier = Modifier.clickable { onEsqueciSenha(email) }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botão Entrar
                    Button(
                        onClick = { onLogin() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colors.primary
                        )
                    ) {
                        Text(
                            text = "Entrar",
                            style = MaterialTheme.typography.titleLarge,
                            color = colors.textOnPrimary
                        )
                    }


                    Spacer(modifier = Modifier.height(16.dp))

                    // Não tem conta? Cadastre-se
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Não tem conta?  ",
                            style = MaterialTheme.typography.bodyMedium,
                            color = colors.textSecondary
                        )
                        Text(
                            text = "Cadastre-se",
                            style = MaterialTheme.typography.labelLarge,
                            color = colors.primary,
                            modifier = Modifier.clickable { onRegister() }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    ComunicacaoEscolarTheme {
        LoginScreen()
    }
}