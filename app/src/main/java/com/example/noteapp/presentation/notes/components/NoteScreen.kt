package com.example.noteapp.presentation.notes.components


import android.annotation.SuppressLint
import android.view.View
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noteapp.Screen
import com.example.noteapp.domain.util.NoteOrder
import com.example.noteapp.domain.util.NotesState
import com.example.noteapp.domain.util.OrderType
import com.example.noteapp.presentation.notes.NotesViewModel
import com.example.noteapp.presentation.util.NotesEvent
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteScreen(
    navController: NavController,
    notesViewModel: NotesViewModel  = hiltViewModel()
) {
    val state = notesViewModel.state.value
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {  navController.navigate(Screen.AddEditNoteScreen.route) },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        },
        scaffoldState = scaffoldState,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Your Note", style = MaterialTheme.typography.subtitle2)
                    IconButton(onClick = { notesViewModel.OnEvent(NotesEvent.ToggleOrderSection) }) {
                        Icon(imageVector = Icons.Default.List, contentDescription = "sort")
                    }
                }
                AnimatedVisibility(
                    visible = state.isOrderSectionVisible,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    OrderSection(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp), state.noteOrder, onOrderChange = {
                        notesViewModel.OnEvent(NotesEvent.Order(it))
                    })
                }
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn(Modifier.fillMaxSize()) {
                    items(state.notes) { note ->
                        NoteItem(
                            note = note,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {  navController.navigate(
                                    Screen.AddEditNoteScreen.route +
                                            "?noteId=${note.id}&noteColor=${note.color}"
                                ) },
                            onDeleteClick = {notesViewModel.OnEvent(NotesEvent.DeleteNote(note))
                                scope.launch {
                                    val result =  scaffoldState.snackbarHostState.showSnackbar(message = "Note Deleted",actionLabel = "Undo")
                                    if (result == SnackbarResult.ActionPerformed ){
                                        notesViewModel.OnEvent(NotesEvent.RestoreNote)
                                    }
                                }
                            })
                        Spacer(modifier = Modifier.height(16.dp))

                    }
                }

            }

        }
    )



}