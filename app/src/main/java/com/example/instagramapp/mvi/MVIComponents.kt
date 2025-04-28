package com.example.instagramapp.mvi

/**
 * MVI base components for the Instagram clone app
 */

// Base State interface - all UI states will implement this
interface UiState

// Base Intent interface - all user intents will implement this
interface UiIntent

// Base Effect interface - all one-time effects will implement this
interface UiEffect

// Base ViewModel interface that defines the contract for all ViewModels
interface MVIViewModel<S : UiState, I : UiIntent, E : UiEffect> {
    // Current UI state
    val state: S
    
    // Process user intents
    fun processIntent(intent: I)
    
    // Observable state for the UI
    fun observeState(): kotlinx.coroutines.flow.StateFlow<S>
    
    // Observable effects for the UI (one-time events)
    fun observeEffect(): kotlinx.coroutines.flow.SharedFlow<E>
}
