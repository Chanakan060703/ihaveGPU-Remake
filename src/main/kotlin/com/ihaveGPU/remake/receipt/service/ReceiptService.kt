package com.ihaveGPU.remake.receipt.service

import com.ihaveGPU.remake.receipt.dto.ReceiptDto
import com.ihaveGPU.remake.receipt.request.CreateReceiptRequest
import com.ihaveGPU.remake.receipt.request.UpdateReceiptRequest

interface ReceiptService {
  fun getReceiptAll(): List<ReceiptDto>
  fun getReceiptById(id: Long): ReceiptDto
  fun getReceiptByEmail(email: String): List<ReceiptDto>
  fun createReceipt(request: CreateReceiptRequest): ReceiptDto
  fun updateReceipt(request: UpdateReceiptRequest): ReceiptDto
  fun deleteReceipt(id: Long): Boolean
}

