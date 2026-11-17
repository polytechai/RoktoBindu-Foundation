package com.roktobindu.foundation.data

import java.util.Calendar
import java.util.concurrent.TimeUnit

object CumillaData {

    const val ORG_NAME = "রক্তবিন্দু ফাউন্ডেশন"
    const val ORG_SLOGAN = "রক্তের টানে, প্রাণের বন্ধনে"
    const val ORG_LOCATION = "কুমিল্লা জেলা"
    const val RECOVERY_DAYS = 90L

    val BLOOD_GROUPS = listOf(
        "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"
    )

    val CUMILLA_UPAZILAS = listOf(
        "আদর্শ সদর",
        "সদর দক্ষিণ",
        "বুড়িচং",
        "দেবিদ্বার",
        "চৌদ্দগ্রাম",
        "লাকসাম",
        "দাউদকান্দি",
        "মুরাদনগর",
        "চান্দিনা",
        "বরুড়া",
        "হোমনা",
        "তিতাস",
        "মেঘনা",
        "মনোহরগঞ্জ",
        "নাঙ্গলকোট",
        "ব্রাহ্মণপাড়া",
        "লালমাই"
    )

    val VOLUNTEER_ROLES = listOf(
        "ক্যাম্পেইন সমন্বয়কারী",
        "রক্তদাতা যোগাযোগ টিম",
        "জরুরি অ্যাম্বুলেন্স সহায়তা",
        "গ্রুপিং বুথ স্বেচ্ছাসেবক",
        "ডিজিটাল ও মিডিয়া টিম"
    )

    val FOUNDATION_TEAM = listOf(
        TeamMember(
            id = "TM-01",
            name = "মোঃ তানভীর আহমেদ মজুমদার",
            role = "প্রতিষ্ঠাতা ও প্রধান উপদেষ্টা",
            badgeTitle = "প্রতিষ্ঠাতা",
            upazila = "আদর্শ সদর",
            bloodGroup = "O+",
            phone = "01711458920"
        ),
        TeamMember(
            id = "TM-02",
            name = "সাইফুল ইসলাম ভুঁইয়া",
            role = "সহ-প্রতিষ্ঠাতা",
            badgeTitle = "সহ-প্রতিষ্ঠাতা",
            upazila = "বুড়িচং",
            bloodGroup = "A+",
            phone = "01819334712"
        ),
        TeamMember(
            id = "TM-03",
            name = "মাহমুদুল হাসান চৌধুরী",
            role = "সভাপতি, কেন্দ্রীয় কার্যনির্বাহী কমিটি",
            badgeTitle = "সভাপতি",
            upazila = "আদর্শ সদর",
            bloodGroup = "B+",
            phone = "01715667890"
        ),
        TeamMember(
            id = "TM-04",
            name = "নুসরাত জাহান তাসনিম",
            role = "সাধারণ সম্পাদক",
            badgeTitle = "সাধারণ সম্পাদক",
            upazila = "দেবিদ্বার",
            bloodGroup = "AB+",
            phone = "01912884501"
        ),
        TeamMember(
            id = "TM-05",
            name = "রাশেদুল করিম পাটোয়ারী",
            role = "সাংগঠনিক সম্পাদক ও সিনিয়র নির্বাহী",
            badgeTitle = "সিনিয়র নির্বাহী",
            upazila = "লাকসাম",
            bloodGroup = "O-",
            phone = "01822910445"
        ),
        TeamMember(
            id = "TM-06",
            name = "কামরুল হাসান সরকার",
            role = "জরুরি রক্ত ব্যবস্থাপনা সমন্বয়ক",
            badgeTitle = "সিনিয়র নির্বাহী",
            upazila = "চৌদ্দগ্রাম",
            bloodGroup = "A-",
            phone = "01733552108"
        )
    )

    val EMERGENCY_HELPLINES = listOf(
        HelplineContact(
            id = "HL-01",
            title = "কুমিল্লা মেডিকেল কলেজ ব্লাড ব্যাংক",
            subtitle = "কুচাইতলী, আদর্শ সদর, কুমিল্লা",
            category = "ব্লাড ব্যাংক",
            phone = "08176524",
            availableHours = "২৪ ঘণ্টা খোলা"
        ),
        HelplineContact(
            id = "HL-02",
            title = "বাংলাদেশ রেড ক্রিসেন্ট রক্ত কেন্দ্র, কুমিল্লা",
            subtitle = "পুলিশ লাইন্স সংলগ্ন, কুমিল্লা",
            category = "ব্লাড ব্যাংক",
            phone = "01711289450",
            availableHours = "সকাল ৮টা - রাত ১০টা"
        ),
        HelplineContact(
            id = "HL-03",
            title = "মুন হসপিটাল ব্লাড ব্যাংক",
            subtitle = "ঝাউতলা, কান্দিরপাড়, কুমিল্লা",
            category = "ব্লাড ব্যাংক",
            phone = "01713360880",
            availableHours = "২৪ ঘণ্টা খোলা"
        ),
        HelplineContact(
            id = "HL-04",
            title = "কুমিল্লা জেনারেল (সদর) হাসপাতাল",
            subtitle = "চকবাজার রোড, কুমিল্লা",
            category = "ব্লাড ব্যাংক",
            phone = "08165110",
            availableHours = "২৪ ঘণ্টা জরুরি বিভাগ"
        ),
        HelplineContact(
            id = "HL-05",
            title = "রক্তবিন্দু কেন্দ্রীয় জরুরি অ্যাম্বুলেন্স",
            subtitle = "সমগ্র কুমিল্লা জেলা অ্যাম্বুলেন্স সাপোর্ট",
            category = "অ্যাম্বুলেন্স",
            phone = "01815990011",
            availableHours = "২৪/৭ জরুরি সেবা"
        ),
        HelplineContact(
            id = "HL-06",
            title = "জাতীয় জরুরি সেবা",
            subtitle = "বাংলাদেশ জাতীয় হেল্পলাইন",
            category = "জরুরি সেবা",
            phone = "999",
            availableHours = "২৪ ঘণ্টা টোল-ফ্রি"
        )
    )

    fun initialMembers(): List<Member> {
        val today = CumillaDateUtils.todayEpochDay()
        val now = System.currentTimeMillis()
        return listOf(
            Member(
                id = "RBF-1001",
                fullName = "আরিফুল ইসলাম চৌধুরী",
                phone = "01712345678",
                bloodGroup = "O+",
                upazila = "আদর্শ সদর",
                lastDonationEpochDays = today - 118,
                status = MembershipStatus.PREMIUM,
                createdAtMillis = now - 12_000_000L
            ),
            Member(
                id = "RBF-1002",
                fullName = "মেহেদী হাসান ভূঁইয়া",
                phone = "01818902341",
                bloodGroup = "A+",
                upazila = "বুড়িচং",
                lastDonationEpochDays = today - 42,
                status = MembershipStatus.PREMIUM,
                createdAtMillis = now - 11_000_000L
            ),
            Member(
                id = "RBF-1003",
                fullName = "ফারহানা আক্তার মিম",
                phone = "01914771209",
                bloodGroup = "B+",
                upazila = "দেবিদ্বার",
                lastDonationEpochDays = today - 140,
                status = MembershipStatus.PREMIUM,
                createdAtMillis = now - 10_000_000L
            ),
            Member(
                id = "RBF-1004",
                fullName = "নাঈমুর রহমান মজুমদার",
                phone = "01725663410",
                bloodGroup = "O-",
                upazila = "লাকসাম",
                lastDonationEpochDays = null,
                status = MembershipStatus.PREMIUM,
                createdAtMillis = now - 9_000_000L
            ),
            Member(
                id = "RBF-1005",
                fullName = "সাব্বির আহমেদ পাটোয়ারী",
                phone = "01831450987",
                bloodGroup = "AB+",
                upazila = "চৌদ্দগ্রাম",
                lastDonationEpochDays = today - 65,
                status = MembershipStatus.PREMIUM,
                createdAtMillis = now - 8_000_000L
            ),
            Member(
                id = "RBF-1006",
                fullName = "তৌহিদুল ইসলাম সরকার",
                phone = "01744228910",
                bloodGroup = "B-",
                upazila = "দাউদকান্দি",
                lastDonationEpochDays = today - 98,
                status = MembershipStatus.PREMIUM,
                createdAtMillis = now - 7_000_000L
            ),
            Member(
                id = "RBF-1007",
                fullName = "তানজিলা সুলতানা রূপা",
                phone = "01920118732",
                bloodGroup = "A-",
                upazila = "সদর দক্ষিণ",
                lastDonationEpochDays = today - 180,
                status = MembershipStatus.PREMIUM,
                createdAtMillis = now - 6_000_000L
            ),
            Member(
                id = "RBF-1008",
                fullName = "জাহিদুল হক মৃধা",
                phone = "01755890123",
                bloodGroup = "O+",
                upazila = "মুরাদনগর",
                lastDonationEpochDays = today - 25,
                status = MembershipStatus.PREMIUM,
                createdAtMillis = now - 5_000_000L
            ),
            Member(
                id = "RBF-1009",
                fullName = "ইমরান হোসেন গাজী",
                phone = "01842773311",
                bloodGroup = "AB-",
                upazila = "চান্দিনা",
                lastDonationEpochDays = today - 105,
                status = MembershipStatus.PREMIUM,
                createdAtMillis = now - 4_000_000L
            ),
            Member(
                id = "RBF-1010",
                fullName = "শাকিল আহমেদ তপু",
                phone = "01789441290",
                bloodGroup = "B+",
                upazila = "বরুড়া",
                lastDonationEpochDays = today - 120,
                status = MembershipStatus.PENDING,
                createdAtMillis = now - 3_000_000L
            ),
            Member(
                id = "RBF-1011",
                fullName = "আয়েশা সিদ্দিকা নিঝুম",
                phone = "01876553421",
                bloodGroup = "A+",
                upazila = "নাঙ্গলকোট",
                lastDonationEpochDays = null,
                status = MembershipStatus.PENDING,
                createdAtMillis = now - 2_000_000L
            ),
            Member(
                id = "RBF-1012",
                fullName = "মোঃ রাকিবুল হাসান",
                phone = "01951208844",
                bloodGroup = "O+",
                upazila = "লালমাই",
                lastDonationEpochDays = today - 95,
                status = MembershipStatus.PENDING,
                createdAtMillis = now - 1_000_000L
            )
        )
    }

    fun initialRequests(): List<BloodRequest> {
        val now = System.currentTimeMillis()
        return listOf(
            BloodRequest(
                id = "REQ-201",
                patientName = "আব্দুর রহমান (সার্জারি ওয়ার্ড)",
                bloodGroup = "O-",
                bagsNeeded = 2,
                hospitalName = "কুমিল্লা মেডিকেল কলেজ হাসপাতাল (কুমেক)",
                upazila = "আদর্শ সদর",
                contactNumber = "01711889944",
                urgencyNote = "জরুরি সড়ক দুর্ঘটনা অপারেশন — আজ বিকাল ৪টার মধ্যে প্রয়োজন",
                postedTimeLabel = "১৫ মিনিট আগে",
                isFulfilled = false,
                createdAtMillis = now - 900_000L
            ),
            BloodRequest(
                id = "REQ-202",
                patientName = "হালিমা বেগম",
                bloodGroup = "B+",
                bagsNeeded = 1,
                hospitalName = "মুন হসপিটাল, ঝাউতলা",
                upazila = "আদর্শ সদর",
                contactNumber = "01819772255",
                urgencyNote = "জরুরি সিজারিয়ান অপারেশন ও হিমোগ্লোবিন স্বল্পতা",
                postedTimeLabel = "৪৫ মিনিট আগে",
                isFulfilled = false,
                createdAtMillis = now - 2_700_000L
            ),
            BloodRequest(
                id = "REQ-203",
                patientName = "শিশু রাফিউল ইসলাম (৮ বছর)",
                bloodGroup = "A+",
                bagsNeeded = 2,
                hospitalName = "টাওয়ার হসপিটাল, কান্দিরপাড়",
                upazila = "আদর্শ সদর",
                contactNumber = "01915334466",
                urgencyNote = "থ্যালাসেমিয়া নিয়মিত রক্ত সঞ্চালন — ফ্রেশ ব্লাড প্রয়োজন",
                postedTimeLabel = "২ ঘণ্টা আগে",
                isFulfilled = false,
                createdAtMillis = now - 7_200_000L
            ),
            BloodRequest(
                id = "REQ-204",
                patientName = "জসিম উদ্দিন মিয়া",
                bloodGroup = "AB+",
                bagsNeeded = 1,
                hospitalName = "লাকসাম উপজেলা স্বাস্থ্য কমপ্লেক্স",
                upazila = "লাকসাম",
                contactNumber = "01722661188",
                urgencyNote = "ডেঙ্গু জ্বর — জরুরি রক্ত ও প্লাটিলেট সহায়তা",
                postedTimeLabel = "৪ ঘণ্টা আগে",
                isFulfilled = false,
                createdAtMillis = now - 14_400_000L
            ),
            BloodRequest(
                id = "REQ-205",
                patientName = "সালমা আক্তার",
                bloodGroup = "O+",
                bagsNeeded = 1,
                hospitalName = "কুমিল্লা ট্রমা সেন্টার, রেইসকোর্স",
                upazila = "আদর্শ সদর",
                contactNumber = "01812559900",
                urgencyNote = "অস্ত্রোপচার পরবর্তী রক্ত স্বল্পতা",
                postedTimeLabel = "গতকাল",
                isFulfilled = true,
                createdAtMillis = now - 86_400_000L
            )
        )
    }

    fun initialNotices(): List<Notice> = listOf(
        Notice(
            id = "NTC-301",
            title = "বিনামূল্যে রক্তের গ্রুপ নির্ণয় ও সচেতনতা ক্যাম্পেইন",
            category = "রক্তের গ্রুপ নির্ণয় ক্যাম্পেইন",
            dateText = "আগামী শুক্রবার, সকাল ৯:০০টা - বিকাল ৪:০০টা",
            location = "কুমিল্লা ভিক্টোরিয়া সরকারি কলেজ প্রাঙ্গণ (ডিগ্রি শাখা)",
            details = "রক্তবিন্দু ফাউন্ডেশনের উদ্যোগে শিক্ষার্থী ও সাধারণ মানুষের জন্য সম্পূর্ণ বিনামূল্যে রক্তের গ্রুপ নির্ণয় এবং স্বেচ্ছায় রক্তদান নিবন্ধন কর্মসূচি অনুষ্ঠিত হবে।",
            isPinned = true
        ),
        Notice(
            id = "NTC-302",
            title = "মাসিক কার্যনির্বাহী ও সমন্বয় সভা — নভেম্বর",
            category = "মাসিক সভা",
            dateText = "আগামী শনিবার, বিকাল ৩:৩০টা",
            location = "কান্দিরপাড় টাউন হল মিলনায়তন, কুমিল্লা",
            details = "উপজেলা ভিত্তিক রক্তদাতা ডাটাবেস হালনাগাদ, জরুরি হেল্পলাইন ২৪/৭ টিম গঠন এবং আসন্ন শীতকালীন ব্লাড ক্যাম্পেইনের পরিকল্পনা অনুমোদনের জন্য সকল সদস্যের উপস্থিতি কাম্য।",
            isPinned = true
        ),
        Notice(
            id = "NTC-303",
            title = "দেবিদ্বার ও বুড়িচং উপজেলায় ভ্রাম্যমাণ রক্তদান কর্মসূচি",
            category = "ক্যাম্পেইন",
            dateText = "২৪ নভেম্বর, সকাল ১০:০০টা",
            location = "দেবিদ্বার নিউ মার্কেট চত্বর, কুমিল্লা",
            details = "থ্যালাসেমিয়া আক্রান্ত শিশুদের সহায়তায় দিনব্যাপী স্বেচ্ছা রক্তদান উৎসব। প্রতিটি ব্যাগ রক্ত কুমিল্লা মেডিকেল কলেজ ব্লাড ব্যাংক ও রেড ক্রিসেন্টের মাধ্যমে যাচাইকৃত রোগীদের মাঝে পৌঁছে দেওয়া হবে।",
            isPinned = false
        ),
        Notice(
            id = "NTC-304",
            title = "নতুন সদস্য যাচাইকরণ ও প্রিমিয়াম আইডি কার্ড বিতরণ",
            category = "সাংগঠনিক ঘোষণা",
            dateText = "চলতি সপ্তাহ",
            location = "রক্তবিন্দু ফাউন্ডেশন কার্যালয়, ঝাউতলা, কুমিল্লা",
            details = "যাঁরা অ্যাপে নতুন সদস্য হিসেবে নিবন্ধন করেছেন, সিনিয়র প্যানেল থেকে ফোন ভেরিফিকেশন সম্পন্ন হওয়ার সাথে সাথেই আপনাদের প্রোফাইল 'প্রিমিয়াম সদস্য' হিসেবে অনুমোদিত হবে।",
            isPinned = false
        )
    )
}

object CumillaDateUtils {
    private val bengaliDigits = charArrayOf('০', '১', '২', '৩', '৪', '৫', '৬', '৭', '৮', '৯')
    private val bengaliMonths = listOf(
        "জানুয়ারি", "ফেব্রুয়ারি", "মার্চ", "এপ্রিল", "মে", "জুন",
        "জুলাই", "আগস্ট", "সেপ্টেম্বর", "অক্টোবর", "নভেম্বর", "ডিসেম্বর"
    )

    fun toBengaliDigits(value: Int): String = toBengaliDigits(value.toString())

    fun toBengaliDigits(value: Long): String = toBengaliDigits(value.toString())

    fun toBengaliDigits(input: String): String {
        val sb = StringBuilder(input.length)
        for (ch in input) {
            if (ch in '0'..'9') {
                sb.append(bengaliDigits[ch - '0'])
            } else {
                sb.append(ch)
            }
        }
        return sb.toString()
    }

    fun todayEpochDay(): Long {
        val cal = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return TimeUnit.MILLISECONDS.toDays(cal.timeInMillis)
    }

    fun millisToEpochDay(millis: Long): Long {
        val cal = Calendar.getInstance().apply {
            timeInMillis = millis
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return TimeUnit.MILLISECONDS.toDays(cal.timeInMillis)
    }

    fun epochDayToMillis(epochDay: Long): Long = TimeUnit.DAYS.toMillis(epochDay)

    fun daysSince(epochDay: Long): Long = (todayEpochDay() - epochDay).coerceAtLeast(0L)

    fun isEligible(lastDonationEpochDays: Long?): Boolean {
        if (lastDonationEpochDays == null) return true
        return daysSince(lastDonationEpochDays) >= CumillaData.RECOVERY_DAYS
    }

    fun remainingRecoveryDays(lastDonationEpochDays: Long?): Long {
        if (lastDonationEpochDays == null) return 0L
        val elapsed = daysSince(lastDonationEpochDays)
        return (CumillaData.RECOVERY_DAYS - elapsed).coerceAtLeast(0L)
    }

    fun formatBengaliDate(epochDay: Long?): String {
        if (epochDay == null) return "এখনো রক্তদান করেননি"
        val cal = Calendar.getInstance().apply {
            timeInMillis = TimeUnit.DAYS.toMillis(epochDay)
        }
        val day = toBengaliDigits(cal.get(Calendar.DAY_OF_MONTH))
        val month = bengaliMonths[cal.get(Calendar.MONTH)]
        val year = toBengaliDigits(cal.get(Calendar.YEAR))
        return "$day $month $year"
    }
}
