package com.example

/*
sealed class OrderType {
    object Ascending:OrderType()
    object Desending :OrderType()

}

sealed class NoteOrder( val orderType: OrderType){
    class Title(orderType: OrderType):NoteOrder(orderType)
    class Date(orderType: OrderType):NoteOrder(orderType)
    class Color(orderType: OrderType):NoteOrder(orderType)
    fun copy(orderType: OrderType): NoteOrder {
        return when(this){
            is NoteOrder.Title -> NoteOrder.Title(orderType)
            is NoteOrder.Date -> NoteOrder.Date(orderType)
            is NoteOrder.Color -> NoteOrder.Color(orderType)
        }
    }

}

data class NotesState(
   val notes: List<Note> = emptyList() ,
    val noteOrder : NoteOrder =NoteOrder.Date(OrderType.Desending),
    val isOrderSectionVisible :Boolean = false
)

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()

}
 */