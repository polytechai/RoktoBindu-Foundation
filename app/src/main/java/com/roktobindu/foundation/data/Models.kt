package com.roktobindu.foundation.data

enum class MembershipStatus(val labelBn: String) {
    PENDING("পেন্ডিং (Pending)"),
    PREMIUM("প্রিমিয়াম সদস্য (Verified)")
}

enum class RequestFilter(val labelBn: String) {
    ACTIVE("সক্রিয় অনুরোধ"),
    FULFILLED("রক্ত পাওয়া গেছে"),
    ALL("সকল")
}

data class Member(
    val id: String,
    val fullName: String,
    val phone: String,
    val bloodGroup: String,
    val upazila: String,
    val lastDonationEpochDays: Long? = null,
    val status: MembershipStatus = MembershipStatus.PENDING,
    val isVolunteer: Boolean = false,
    val createdAtMillis: Long = System.currentTimeMillis()
)

data class BloodRequest(
    val id: String,
    val patientName: String,
    val bloodGroup: String,
    val bagsNeeded: Int,
    val hospitalName: String,
    val upazila: String,
    val contactNumber: String,
    val urgencyNote: String,
    val postedTimeLabel: String,
    val isFulfilled: Boolean = false,
    val createdAtMillis: Long = System.currentTimeMillis()
)

data class Notice(
    val id: String,
    val title: String,
    val category: String,
    val dateText: String,
    val location: String,
    val details: String,
    val isPinned: Boolean = false
)

data class VolunteerApplication(
    val id: String,
    val fullName: String,
    val phone: String,
    val upazila: String,
    val bloodGroup: String,
    val roleArea: String,
    val createdAtMillis: Long = System.currentTimeMillis()
)

data class TeamMember(
    val id: String,
    val name: String,
    val role: String,
    val badgeTitle: String,
    val upazila: String,
    val bloodGroup: String,
    val phone: String
)

data class HelplineContact(
    val id: String,
    val title: String,
    val subtitle: String,
    val category: String,
    val phone: String,
    val availableHours: String
)
