package com.example.model.accounts

sealed class AccountCreationResult {
    data class Success(val userId: Long) : AccountCreationResult()
    data class Error(val message: String) : AccountCreationResult()
}