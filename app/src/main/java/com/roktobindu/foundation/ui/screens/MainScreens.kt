package com.roktobindu.foundation.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Bloodtype
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.LocalPhone
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roktobindu.foundation.data.BloodRequest
import com.roktobindu.foundation.data.CumillaData
import com.roktobindu.foundation.data.CumillaDateUtils
import com.roktobindu.foundation.data.Member
import com.roktobindu.foundation.data.MembershipStatus
import com.roktobindu.foundation.data.Notice
import com.roktobindu.foundation.data.RequestFilter
import com.roktobindu.foundation.ui.theme.AmberContainer
import com.roktobindu.foundation.ui.theme.AmberWaiting
import com.roktobindu.foundation.ui.theme.CrimsonDark
import com.roktobindu.foundation.ui.theme.CrimsonPrimary
import com.roktobindu.foundation.ui.theme.CrimsonSoft
import com.roktobindu.foundation.ui.theme.EmeraldContainer
import com.roktobindu.foundation.ui.theme.EmeraldVerified
import com.roktobindu.foundation.ui.theme.OutlineSubtle
import com.roktobindu.foundation.ui.theme.PureWhite

private data class QuickActionItem(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val tint: Color,
    val bg: Color,
    val onClick: () -> Unit
)

@Composable
fun HomeScreen(
    totalDonors: Int,
    eligibleDonorsCount: Int,
    topActiveRequests: List<BloodRequest>,
    onNavigateToFindDonor: () -> Unit,
    onNavigateToRequests: () -> Unit,
    onOpenEmergencyRequestSheet: () -> Unit,
    onOpenDonorRegistrationSheet: () -> Unit,
    onOpenVolunteerRegistrationSheet: () -> Unit,
    onOpenTeamSheet: () -> Unit,
    onOpenHelplineSheet: () -> Unit,
    onCallPhone: (String) -> Unit
) {
    val quickActions = listOf(
        QuickActionItem(
            title = "রক্তদাতা খুঁজুন",
            subtitle = "গ্রুপ ও উপজেলা ভিত্তিক",
            icon = Icons.Filled.Search,
            tint = CrimsonPrimary,
            bg = CrimsonSoft,
            onClick = onNavigateToFindDonor
        ),
        QuickActionItem(
            title = "রক্তের অনুরোধ",
            subtitle = "জরুরি পোস্ট করুন",
            icon = Icons.Filled.Bloodtype,
            tint = CrimsonDark,
            bg = CrimsonSoft,
            onClick = onOpenEmergencyRequestSheet
        ),
        QuickActionItem(
            title = "রক্তদাতা নিবন্ধন",
            subtitle = "নতুন দাতা যুক্ত হন",
            icon = Icons.Filled.PersonAdd,
            tint = EmeraldVerified,
            bg = EmeraldContainer,
            onClick = onOpenDonorRegistrationSheet
        ),
        QuickActionItem(
            title = "স্বেচ্ছাসেবক নিবন্ধন",
            subtitle = "টিমে যোগ দিন",
            icon = Icons.Filled.VolunteerActivism,
            tint = Color(0xFF0277BD),
            bg = Color(0xFFE1F5FE),
            onClick = onOpenVolunteerRegistrationSheet
        ),
        QuickActionItem(
            title = "আমাদের টিম",
            subtitle = "প্রতিষ্ঠাতা ও নির্বাহী প্যানেল",
            icon = Icons.Filled.Groups,
            tint = Color(0xFF6A1B9A),
            bg = Color(0xFFF3E5F5),
            onClick = onOpenTeamSheet
        ),
        QuickActionItem(
            title = "জরুরি হেল্পলাইন",
            subtitle = "ব্লাড ব্যাংক ও অ্যাম্বুলেন্স",
            icon = Icons.Filled.LocalPhone,
            tint = AmberWaiting,
            bg = AmberContainer,
            onClick = onOpenHelplineSheet
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        item {
            Card(
                shape = RoundedCornerShape(22.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                colors = CardDefaults.cardColors(containerColor = PureWhite),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.linearGradient(
                                colors = listOf(Color(0xFFFFF5F5), PureWhite)
                            )
                        )
                        .padding(18.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = CrimsonSoft,
                        border = BorderStroke(1.dp, CrimsonPrimary.copy(alpha = 0.25f))
                    ) {
                        Text(
                            text = "কুমিল্লা জেলা স্বেচ্ছাসেবী নেটওয়ার্ক",
                            style = MaterialTheme.typography.labelMedium,
                            color = CrimsonDark,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "এক ফোঁটা রক্ত,\nএকটি নতুন জীবন",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "সমগ্র কুমিল্লায় স্বেচ্ছায় নিরাপদ রক্ত পৌঁছে দিতে আমরা সর্বদা প্রস্তুত।",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        StatBadge(
                            value = CumillaDateUtils.toBengaliDigits(totalDonors),
                            label = "মোট রক্তদাতা",
                            accent = CrimsonPrimary
                        )
                        StatBadge(
                            value = CumillaDateUtils.toBengaliDigits(eligibleDonorsCount),
                            label = "সক্ষম দাতা",
                            accent = EmeraldVerified
                        )
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    Button(
                        onClick = onOpenEmergencyRequestSheet,
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = CrimsonPrimary,
                            contentColor = Color.White
                        ),
                        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Bloodtype,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("জরুরি রক্তের অনুরোধ করুন", style = MaterialTheme.typography.labelLarge)
                    }
                }
            }
        }

        item {
            Column {
                Text(
                    text = "দ্রুত সেবা সমূহ",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(10.dp))
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    quickActions.chunked(2).forEach { rowItems ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            rowItems.forEach { item ->
                                Card(
                                    onClick = item.onClick,
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(16.dp),
                                    colors = CardDefaults.cardColors(containerColor = PureWhite),
                                    border = BorderStroke(1.dp, OutlineSubtle),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                                ) {
                                    Column(modifier = Modifier.padding(14.dp)) {
                                        Box(
                                            modifier = Modifier
                                                .size(42.dp)
                                                .clip(RoundedCornerShape(12.dp))
                                                .background(item.bg),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = item.icon,
                                                contentDescription = item.title,
                                                tint = item.tint,
                                                modifier = Modifier.size(22.dp)
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Text(
                                            text = item.title,
                                            style = MaterialTheme.typography.titleSmall,
                                            fontWeight = FontWeight.Bold,
                                            maxLines = 1
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = item.subtitle,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            maxLines = 1
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "জরুরি রক্তের অনুরোধ",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "কুমিল্লা জেলার সাম্প্রতিক জরুরি প্রয়োজন",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                TextButton(onClick = onNavigateToRequests) {
                    Text("সবগুলো দেখুন", color = CrimsonPrimary, fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = CrimsonPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        items(topActiveRequests, key = { it.id }) { request ->
            BloodRequestCard(
                request = request,
                showFulfillToggle = false,
                onToggleFulfilled = {},
                onCallPhone = onCallPhone
            )
        }
    }
}

@Composable
private fun StatBadge(value: String, label: String, accent: Color) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = PureWhite,
        border = BorderStroke(1.dp, OutlineSubtle)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleSmall,
                color = accent,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun FindDonorScreen(
    donors: List<Member>,
    selectedBloodGroup: String,
    selectedUpazila: String,
    searchQuery: String,
    onlyEligible: Boolean,
    onBloodGroupSelected: (String) -> Unit,
    onUpazilaSelected: (String) -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onToggleOnlyEligible: () -> Unit,
    onResetFilters: () -> Unit,
    onCallPhone: (String) -> Unit
) {
    val bloodGroupOptions = listOf("সকল") + CumillaData.BLOOD_GROUPS
    val upazilaOptions = listOf("সকল") + CumillaData.CUMILLA_UPAZILAS

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Surface(color = PureWhite, shadowElevation = 2.dp) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = onSearchQueryChanged,
                    placeholder = { Text("নাম, উপজেলা বা ফোন দিয়ে খুঁজুন...") },
                    leadingIcon = {
                        Icon(Icons.Filled.Search, contentDescription = "খুঁজুন", tint = CrimsonPrimary)
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { onSearchQueryChanged("") }) {
                                Icon(Icons.Filled.Clear, contentDescription = "মুছুন")
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    DropdownSelectorField(
                        label = "রক্তের গ্রুপ",
                        selectedValue = selectedBloodGroup,
                        options = bloodGroupOptions,
                        onOptionSelected = onBloodGroupSelected,
                        modifier = Modifier.weight(0.44f)
                    )
                    DropdownSelectorField(
                        label = "কুমিল্লার উপজেলা",
                        selectedValue = selectedUpazila,
                        options = upazilaOptions,
                        onOptionSelected = onUpazilaSelected,
                        modifier = Modifier.weight(0.56f)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilterChip(
                        selected = onlyEligible,
                        onClick = onToggleOnlyEligible,
                        label = { Text("শুধুমাত্র রক্তদানে সক্ষম") },
                        leadingIcon = if (onlyEligible) {
                            {
                                Icon(
                                    imageVector = Icons.Filled.Check,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        } else {
                            null
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = EmeraldContainer,
                            selectedLabelColor = EmeraldVerified,
                            selectedLeadingIconColor = EmeraldVerified
                        )
                    )
                    Text(
                        text = "পাওয়া গেছে: ${CumillaDateUtils.toBengaliDigits(donors.size)} জন",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        if (donors.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    tint = CrimsonPrimary.copy(alpha = 0.5f),
                    modifier = Modifier.size(56.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text("কোনো রক্তদাতা পাওয়া যায়নি", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(onClick = onResetFilters) {
                    Text("ফিল্টার রিসেট করুন", color = CrimsonPrimary)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(donors, key = { it.id }) { donor ->
                    DonorCard(donor = donor, onCallPhone = onCallPhone)
                }
            }
        }
    }
}

@Composable
private fun DonorCard(
    donor: Member,
    onCallPhone: (String) -> Unit
) {
    val isPremium = donor.status == MembershipStatus.PREMIUM
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = PureWhite),
        border = BorderStroke(1.dp, OutlineSubtle),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BloodGroupBadge(bloodGroup = donor.bloodGroup, size = 50)
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = donor.fullName,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.weight(1f, fill = false)
                        )
                        if (isPremium) {
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(
                                imageVector = Icons.Filled.Verified,
                                contentDescription = "প্রিমিয়াম সদস্য",
                                tint = EmeraldVerified,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(3.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = null,
                            tint = CrimsonPrimary,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${donor.upazila}, কুমিল্লা",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.CalendarMonth,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "সর্বশেষ রক্তদান: ${CumillaDateUtils.formatBengaliDate(donor.lastDonationEpochDays)}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            EligibilityBadge(lastDonationEpochDays = donor.lastDonationEpochDays)
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { onCallPhone(donor.phone) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CrimsonPrimary,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(vertical = 10.dp)
            ) {
                Icon(Icons.Filled.Call, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("কল করুন (${donor.phone})", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

@Composable
fun BloodRequestsScreen(
    requests: List<BloodRequest>,
    activeFilter: RequestFilter,
    onFilterChanged: (RequestFilter) -> Unit,
    onToggleFulfilled: (String, Boolean) -> Unit,
    onOpenNewRequestSheet: () -> Unit,
    onCallPhone: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Surface(color = PureWhite, shadowElevation = 2.dp) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    RequestFilter.entries.forEach { filter ->
                        FilterChip(
                            selected = activeFilter == filter,
                            onClick = { onFilterChanged(filter) },
                            label = { Text(filter.labelBn) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = if (filter == RequestFilter.FULFILLED) {
                                    EmeraldContainer
                                } else {
                                    CrimsonSoft
                                },
                                selectedLabelColor = if (filter == RequestFilter.FULFILLED) {
                                    EmeraldVerified
                                } else {
                                    CrimsonPrimary
                                }
                            )
                        )
                    }
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 92.dp
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(requests, key = { it.id }) { req ->
                    BloodRequestCard(
                        request = req,
                        showFulfillToggle = true,
                        onToggleFulfilled = { fulfilled -> onToggleFulfilled(req.id, fulfilled) },
                        onCallPhone = onCallPhone
                    )
                }
            }
        }

        ExtendedFloatingActionButton(
            onClick = onOpenNewRequestSheet,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            containerColor = CrimsonPrimary,
            contentColor = Color.White,
            icon = { Icon(Icons.Filled.Add, contentDescription = "নতুন রক্তের অনুরোধ") },
            text = { Text("রক্তের অনুরোধ", style = MaterialTheme.typography.labelLarge) }
        )
    }
}

@Composable
fun BloodRequestCard(
    request: BloodRequest,
    showFulfillToggle: Boolean,
    onToggleFulfilled: (Boolean) -> Unit,
    onCallPhone: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (request.isFulfilled) Color(0xFFF3F5F7) else PureWhite
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (request.isFulfilled) OutlineSubtle else CrimsonPrimary.copy(alpha = 0.16f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (request.isFulfilled) 0.dp else 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
                BloodGroupBadge(bloodGroup = request.bloodGroup, size = 50)
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(50),
                            color = if (request.isFulfilled) EmeraldContainer else CrimsonSoft
                        ) {
                            Text(
                                text = if (request.isFulfilled) {
                                    "রক্ত পাওয়া গেছে"
                                } else {
                                    "${CumillaDateUtils.toBengaliDigits(request.bagsNeeded)} ব্যাগ প্রয়োজন"
                                },
                                style = MaterialTheme.typography.labelMedium,
                                color = if (request.isFulfilled) EmeraldVerified else CrimsonPrimary,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.AccessTime,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = request.postedTimeLabel,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(request.patientName, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.LocalHospital,
                            contentDescription = null,
                            tint = CrimsonPrimary,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = request.hospitalName,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "${request.upazila}, কুমিল্লা",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            if (request.urgencyNote.isNotBlank()) {
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = if (request.isFulfilled) Color(0xFFEAECEF) else Color(0xFFFFF8F8),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Bloodtype,
                            contentDescription = null,
                            tint = CrimsonPrimary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(request.urgencyNote, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (showFulfillToggle) {
                    OutlinedButton(
                        onClick = { onToggleFulfilled(!request.isFulfilled) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp),
                        border = BorderStroke(
                            1.dp,
                            if (request.isFulfilled) OutlineSubtle else EmeraldVerified
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = null,
                            tint = if (request.isFulfilled) {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            } else {
                                EmeraldVerified
                            },
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (request.isFulfilled) "পুনরায় সক্রিয়" else "রক্ত পাওয়া গেছে",
                            color = if (request.isFulfilled) {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            } else {
                                EmeraldVerified
                            },
                            style = MaterialTheme.typography.labelMedium,
                            maxLines = 1
                        )
                    }
                }

                Button(
                    onClick = { onCallPhone(request.contactNumber) },
                    modifier = if (showFulfillToggle) Modifier.weight(1f) else Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (request.isFulfilled) Color(0xFF6B7280) else CrimsonPrimary,
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Call,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "কল করুন (${request.contactNumber})",
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun NoticesScreen(notices: List<Notice>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = CrimsonSoft,
                border = BorderStroke(1.dp, CrimsonPrimary.copy(alpha = 0.2f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Campaign,
                        contentDescription = null,
                        tint = CrimsonPrimary,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "নোটিশ বোর্ড ও ক্যাম্পেইন",
                            style = MaterialTheme.typography.titleMedium,
                            color = CrimsonPrimary
                        )
                        Text(
                            text = "কুমিল্লা জেলার আসন্ন গ্রুপিং ক্যাম্পেইন, মাসিক সভা ও জরুরি ঘোষণা",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        items(notices, key = { it.id }) { notice ->
            val isCampaign = notice.category.contains("ক্যাম্পেইন")
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = PureWhite),
                border = BorderStroke(
                    1.dp,
                    if (notice.isPinned) CrimsonPrimary.copy(alpha = 0.25f) else OutlineSubtle
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = if (notice.isPinned) 2.dp else 1.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(50),
                            color = if (isCampaign) EmeraldContainer else CrimsonSoft
                        ) {
                            Text(
                                text = notice.category,
                                style = MaterialTheme.typography.labelMedium,
                                color = if (isCampaign) EmeraldVerified else CrimsonPrimary,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                        if (notice.isPinned) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Filled.PushPin,
                                    contentDescription = null,
                                    tint = CrimsonPrimary,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "গুরুত্বপূর্ণ",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = CrimsonPrimary
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(notice.title, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.CalendarToday,
                            contentDescription = null,
                            tint = CrimsonPrimary,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = notice.dateText,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = notice.location,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = notice.details,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    currentUser: Member?,
    pendingMembersCount: Int,
    onRegisterMember: (String, String, String, String) -> Unit,
    onLoginByPhone: (String) -> Unit,
    onLogout: () -> Unit,
    onUpdateLastDonationDate: (String, Long?) -> Unit,
    onOpenSeniorPanel: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (currentUser == null) {
            var selectedAuthTab by remember { mutableIntStateOf(0) }
            var fullName by remember { mutableStateOf("") }
            var phone by remember { mutableStateOf("") }
            var bloodGroup by remember { mutableStateOf(CumillaData.BLOOD_GROUPS.first()) }
            var upazila by remember { mutableStateOf(CumillaData.CUMILLA_UPAZILAS.first()) }
            var loginPhone by remember { mutableStateOf("") }

            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = PureWhite),
                border = BorderStroke(1.dp, OutlineSubtle),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("রক্তবিন্দু সদস্য অ্যাকাউন্ট", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "নিবন্ধনের পর স্ট্যাটাস 'পেন্ডিং' থাকবে এবং সিনিয়র অনুমোদনের পর প্রিমিয়াম ব্যাজ পাবেন।",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    TabRow(
                        selectedTabIndex = selectedAuthTab,
                        containerColor = CrimsonSoft.copy(alpha = 0.5f),
                        contentColor = CrimsonPrimary,
                        modifier = Modifier.clip(RoundedCornerShape(12.dp))
                    ) {
                        Tab(
                            selected = selectedAuthTab == 0,
                            onClick = { selectedAuthTab = 0 },
                            text = { Text("নতুন নিবন্ধন", fontWeight = FontWeight.SemiBold) }
                        )
                        Tab(
                            selected = selectedAuthTab == 1,
                            onClick = { selectedAuthTab = 1 },
                            text = { Text("ফোন দিয়ে লগইন", fontWeight = FontWeight.SemiBold) }
                        )
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    if (selectedAuthTab == 0) {
                        OutlinedTextField(
                            value = fullName,
                            onValueChange = { fullName = it },
                            label = { Text("পূর্ণ নাম (Full Name)") },
                            singleLine = true,
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedTextField(
                            value = phone,
                            onValueChange = { phone = it },
                            label = { Text("ফোন নাম্বার (Phone Number)") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            singleLine = true,
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            DropdownSelectorField(
                                label = "রক্তের গ্রুপ",
                                selectedValue = bloodGroup,
                                options = CumillaData.BLOOD_GROUPS,
                                onOptionSelected = { bloodGroup = it },
                                modifier = Modifier.weight(0.45f)
                            )
                            DropdownSelectorField(
                                label = "কুমিল্লা উপজেলা",
                                selectedValue = upazila,
                                options = CumillaData.CUMILLA_UPAZILAS,
                                onOptionSelected = { upazila = it },
                                modifier = Modifier.weight(0.55f)
                            )
                        }
                        Spacer(modifier = Modifier.height(14.dp))
                        Button(
                            onClick = { onRegisterMember(fullName, phone, bloodGroup, upazila) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = CrimsonPrimary,
                                contentColor = Color.White
                            ),
                            contentPadding = PaddingValues(vertical = 13.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.PersonAdd,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("সদস্য হিসেবে নিবন্ধন করুন")
                        }
                    } else {
                        OutlinedTextField(
                            value = loginPhone,
                            onValueChange = { loginPhone = it },
                            label = { Text("নিবন্ধিত ফোন নাম্বার") },
                            placeholder = { Text("উদাহরণ: 01712345678") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            singleLine = true,
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        Button(
                            onClick = { onLoginByPhone(loginPhone) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = CrimsonPrimary,
                                contentColor = Color.White
                            ),
                            contentPadding = PaddingValues(vertical = 13.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Login,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("লগইন করুন")
                        }
                    }
                }
            }
        } else {
            val isPremium = currentUser.status == MembershipStatus.PREMIUM
            Card(
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = PureWhite),
                border = BorderStroke(
                    1.5.dp,
                    if (isPremium) EmeraldVerified.copy(alpha = 0.35f) else AmberWaiting.copy(alpha = 0.35f)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Brush.horizontalGradient(listOf(CrimsonPrimary, CrimsonDark)))
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "রক্তবিন্দু ফাউন্ডেশন • সদস্য কার্ড",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = Color.White.copy(alpha = 0.9f)
                                )
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Filled.Badge,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "আইডি: ${currentUser.id}",
                                        style = MaterialTheme.typography.titleMedium,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                            MembershipStatusBadge(status = currentUser.status)
                        }
                    }
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            BloodGroupBadge(bloodGroup = currentUser.bloodGroup, size = 56)
                            Spacer(modifier = Modifier.width(14.dp))
                            Column {
                                Text(currentUser.fullName, style = MaterialTheme.typography.headlineSmall)
                                Spacer(modifier = Modifier.height(4.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Filled.Phone,
                                        contentDescription = null,
                                        tint = CrimsonPrimary,
                                        modifier = Modifier.size(15.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(currentUser.phone, style = MaterialTheme.typography.bodyMedium)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Filled.LocationOn,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.size(15.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "${currentUser.upazila}, কুমিল্লা",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // 90-day Donation Recovery Countdown Card
            var showDatePicker by remember { mutableStateOf(false) }
            val lastEpoch = currentUser.lastDonationEpochDays
            val isEligible = CumillaDateUtils.isEligible(lastEpoch)
            val daysPassed = if (lastEpoch == null) 90L else CumillaDateUtils.daysSince(lastEpoch)
            val remainingDays = CumillaDateUtils.remainingRecoveryDays(lastEpoch)
            val progress = (daysPassed.toFloat() / CumillaData.RECOVERY_DAYS.toFloat()).coerceIn(0f, 1f)

            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = PureWhite),
                border = BorderStroke(1.dp, OutlineSubtle),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.CalendarMonth,
                            contentDescription = null,
                            tint = CrimsonPrimary,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "সর্বশেষ রক্তদান ও ৯০ দিনের কাউন্টডাউন",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = if (isEligible) EmeraldContainer else AmberContainer,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Filled.CheckCircle,
                                    contentDescription = null,
                                    tint = if (isEligible) EmeraldVerified else AmberWaiting,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (isEligible) {
                                        "আপনি এখন রক্তদানের জন্য সম্পূর্ণ সক্ষম!"
                                    } else {
                                        "পরবর্তী রক্তদানের জন্য ${CumillaDateUtils.toBengaliDigits(remainingDays)} দিন বাকি"
                                    },
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isEligible) EmeraldVerified else Color(0xFF92400E)
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "সর্বশেষ রক্তদান: ${CumillaDateUtils.formatBengaliDate(lastEpoch)}",
                                style = MaterialTheme.typography.bodySmall
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            LinearProgressIndicator(
                                progress = { progress },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                                    .clip(RoundedCornerShape(50)),
                                color = if (isEligible) EmeraldVerified else AmberWaiting,
                                trackColor = PureWhite.copy(alpha = 0.7f)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = if (lastEpoch == null) {
                                    "৯০ দিনের রিকভারি সম্পন্ন"
                                } else {
                                    "অতিবাহিত: ${CumillaDateUtils.toBengaliDigits(daysPassed.coerceAtMost(90L))} / ৯০ দিন"
                                },
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = { showDatePicker = true },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = CrimsonPrimary,
                                contentColor = Color.White
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Filled.EditCalendar,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("তারিখ পরিবর্তন", style = MaterialTheme.typography.labelMedium)
                        }
                        OutlinedButton(
                            onClick = {
                                onUpdateLastDonationDate(
                                    currentUser.id,
                                    CumillaDateUtils.todayEpochDay()
                                )
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, CrimsonPrimary.copy(alpha = 0.4f))
                        ) {
                            Text(
                                text = "আজ রক্তদান করেছি",
                                color = CrimsonPrimary,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                    if (lastEpoch != null) {
                        TextButton(
                            onClick = { onUpdateLastDonationDate(currentUser.id, null) },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Refresh,
                                contentDescription = null,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("তারিখ রিসেট করুন")
                        }
                    }
                }
            }

            if (showDatePicker) {
                val initialMillis = lastEpoch?.let { CumillaDateUtils.epochDayToMillis(it) }
                    ?: System.currentTimeMillis()
                val datePickerState = rememberDatePickerState(initialSelectedDateMillis = initialMillis)
                DatePickerDialog(
                    onDismissRequest = { showDatePicker = false },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                datePickerState.selectedDateMillis?.let { millis ->
                                    val epoch = CumillaDateUtils.millisToEpochDay(millis)
                                        .coerceAtMost(CumillaDateUtils.todayEpochDay())
                                    onUpdateLastDonationDate(currentUser.id, epoch)
                                }
                                showDatePicker = false
                            }
                        ) {
                            Text("সংরক্ষণ করুন", color = CrimsonPrimary, fontWeight = FontWeight.Bold)
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDatePicker = false }) { Text("বাতিল") }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }

            OutlinedButton(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, OutlineSubtle)
            ) {
                Icon(
                    imageVector = Icons.Filled.Logout,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "সাইন আউট / নতুন সদস্য নিবন্ধন করুন",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Card(
            onClick = onOpenSeniorPanel,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = CrimsonSoft.copy(alpha = 0.7f)),
            border = BorderStroke(1.dp, CrimsonPrimary.copy(alpha = 0.25f))
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = CrimsonPrimary,
                    modifier = Modifier.size(44.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Filled.AdminPanelSettings,
                            contentDescription = "সিনিয়র প্যানেল",
                            tint = Color.White
                        )
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "সিনিয়র প্যানেল (Admin Panel)",
                        style = MaterialTheme.typography.titleMedium,
                        color = CrimsonDark
                    )
                    Text(
                        text = "পেন্ডিং আবেদন: ${CumillaDateUtils.toBengaliDigits(pendingMembersCount)} জন • অনুমোদন ও যাচাই করুন",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun BloodGroupBadge(
    bloodGroup: String,
    size: Int = 48
) {
    Surface(
        modifier = Modifier.size(size.dp),
        shape = RoundedCornerShape(14.dp),
        color = CrimsonPrimary,
        shadowElevation = 2.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = bloodGroup,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = if (bloodGroup.length >= 3) 15.sp else 17.sp
            )
        }
    }
}

@Composable
fun MembershipStatusBadge(status: MembershipStatus) {
    val isPremium = status == MembershipStatus.PREMIUM
    val bg = if (isPremium) EmeraldContainer else AmberContainer
    val fg = if (isPremium) EmeraldVerified else AmberWaiting
    val label = if (isPremium) "প্রিমিয়াম সদস্য" else "পেন্ডিং"
    val icon = if (isPremium) Icons.Filled.Verified else Icons.Filled.HourglassTop

    Surface(
        shape = RoundedCornerShape(50),
        color = bg,
        border = BorderStroke(1.dp, fg.copy(alpha = 0.25f))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = label, tint = fg, modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = fg,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun EligibilityBadge(lastDonationEpochDays: Long?) {
    val eligible = CumillaDateUtils.isEligible(lastDonationEpochDays)
    val remaining = CumillaDateUtils.remainingRecoveryDays(lastDonationEpochDays)
    val bg = if (eligible) EmeraldContainer else AmberContainer
    val fg = if (eligible) EmeraldVerified else AmberWaiting
    val text = if (eligible) {
        "রক্তদানে সক্ষম"
    } else {
        "অপেক্ষমাণ (৯০ দিন পূর্ণ হয়নি • ${CumillaDateUtils.toBengaliDigits(remaining)} দিন বাকি)"
    }

    Surface(
        shape = RoundedCornerShape(50),
        color = bg,
        border = BorderStroke(1.dp, fg.copy(alpha = 0.2f))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(7.dp)
                    .clip(CircleShape)
                    .background(fg)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = fg,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownSelectorField(
    label: String,
    selectedValue: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedValue,
            onValueChange = {},
            readOnly = true,
            label = { Text(label, maxLines = 1, overflow = TextOverflow.Ellipsis) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable, enabled = true)
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option, style = MaterialTheme.typography.bodyMedium) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}
