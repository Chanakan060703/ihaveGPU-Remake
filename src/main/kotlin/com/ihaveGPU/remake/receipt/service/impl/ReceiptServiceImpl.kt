package com.ihaveGPU.remake.receipt.service.impl

import com.ihaveGPU.remake.address.repository.AddressRepository
import com.ihaveGPU.remake.common.exception.NotFoundException
import com.ihaveGPU.remake.entity.Receipt
import com.ihaveGPU.remake.receipt.dto.ReceiptDto
import com.ihaveGPU.remake.receipt.repository.ReceiptRepository
import com.ihaveGPU.remake.receipt.request.CreateReceiptRequest
import com.ihaveGPU.remake.receipt.request.UpdateReceiptRequest
import com.ihaveGPU.remake.receipt.service.ReceiptService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReceiptServiceImpl @Autowired constructor(
  private val receiptRepository: ReceiptRepository,
  private val addressRepository: AddressRepository
): ReceiptService {
  override fun getReceiptAll(): List<ReceiptDto> {
    return receiptRepository.findAllByIsDeletedFalse().map { it.toReceiptDto() }
  }

  override fun getReceiptById(id: Long): ReceiptDto {
    val receipt = receiptRepository.findByIdAndIsDeletedFalse(id)
      ?: throw NotFoundException("Receipt not found")
    return receipt.toReceiptDto()
  }

  override fun getReceiptByEmail(email: String): List<ReceiptDto> {
    return receiptRepository.findAllByEmailAndIsDeletedFalse(email).map { it.toReceiptDto() }
  }

  override fun createReceipt(request: CreateReceiptRequest): ReceiptDto {
    val address = addressRepository.findByIdAndIsDeletedFalse(request.addressId)
      ?: throw NotFoundException("Address not found")

    val receipt = Receipt(
      date = request.date,
      total = request.total,
      email = request.email,
      addressId = address.id
    )
    receiptRepository.save(receipt)
    return receipt.toReceiptDto()
  }

  override fun updateReceipt(request: UpdateReceiptRequest): ReceiptDto {
    val receipt = receiptRepository.findByIdAndIsDeletedFalse(request.id)
      ?: throw NotFoundException("Receipt not found")
    val address = addressRepository.findByIdAndIsDeletedFalse(request.addressId)
      ?: throw NotFoundException("Address not found")

    receipt.date = request.date
    receipt.total = request.total
    receipt.email = request.email
    receipt.addressId = address.id
    receiptRepository.save(receipt)
    return receipt.toReceiptDto()
  }

  override fun deleteReceipt(id: Long): Boolean {
    val receipt = receiptRepository.findById(id)
      .orElseThrow { NotFoundException("Receipt not found") }

    receipt.isDeleted = true
    receiptRepository.save(receipt)

    return true
  }
}

