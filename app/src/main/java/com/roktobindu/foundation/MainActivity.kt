package com.roktobindu.foundation

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Bloodtype
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.outlined.Bloodtype
import androidx.compose.material.icons.outlined.Campaign
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.roktobindu.foundation.data.BloodRequest
import com.roktobindu.foundation.data.CumillaData
import com.roktobindu.foundation.data.CumillaDateUtils
import com.roktobindu.foundation.data.Member
import com.roktobindu.foundation.data.MembershipStatus
import com.roktobindu.foundation.data.Notice
import com.roktobindu.foundation.data.RequestFilter
import com.roktobindu.foundation.data.VolunteerApplication
import com.roktobindu.foundation.ui.screens.BloodGroupBadge
import com.roktobindu.foundation.ui.screens.BloodRequestsScreen
import com.roktobindu.foundation.ui.screens.DropdownSelectorField
import com.roktobindu.foundation.ui.screens.FindDonorScreen
import com.roktobindu.foundation.ui.screens.HomeScreen
import com.roktobindu.foundation.ui.screens.MembershipStatusBadge
import com.roktobindu.foundation.ui.screens.NoticesScreen
import com.roktobindu.foundation.ui.screens.ProfileScreen
import com.roktobindu.foundation.ui.theme.AmberContainer
import com.roktobindu.foundation.ui.theme.AmberWaiting
import com.roktobindu.foundation.ui.theme.CrimsonDark
import com.roktobindu.foundation.ui.theme.CrimsonPrimary
import com.roktobindu.foundation.ui.theme.CrimsonSoft
import com.roktobindu.foundation.ui.theme.EmeraldContainer
import com.roktobindu.foundation.ui.theme.EmeraldVerified
import com.roktobindu.foundation.ui.theme.OutlineSubtle
import com.roktobindu.foundation.ui.theme.PureWhite
import com.roktobindu.foundation.ui.theme.RoktobinduTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RoktobinduTheme {
                RoktobinduApp()
            }
        }
    }
}

enum class BottomNavTab(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    HOME("হোম", Icons.Filled.Home, Icons.Outlined.Home),
    FIND_DONOR("রক্তদাতা", Icons.Filled.Search, Icons.Outlined.Search),
    REQUESTS("অনুরোধ", Icons.Filled.Bloodtype, Icons.Outlined.Bloodtype),
    NOTICES("নোটিশ", Icons.Filled.Campaign, Icons.Outlined.Campaign),
    PROFILE("প্রোফাইল", Icons.Filled.Person, Icons.Outlined.Person)
}

enum class ActiveOverlay {
    NONE,
    SENIOR_PANEL,
    EMERGENCY_REQUEST,
    DONOR_REGISTRATION,
    VOLUNTEER_REGISTRATION,
    OUR_TEAM,
    HELPLINE
}

data class RoktobinduUiState(
    val members: List<Member> = CumillaData.initialMembers(),
    val currentUserId: String? = null,
    val bloodRequests: List<BloodRequest> = CumillaData.initialRequests(),
    val notices: List<Notice> = CumillaData.initialNotices(),
    val volunteers: List<VolunteerApplication> = emptyList(),
    val selectedBloodGroup: String = "সকল",
    val selectedUpazila: String = "সকল",
    val searchQuery: String = "",
    val onlyEligibleDonors: Boolean = false,
    val requestFilter: RequestFilter = RequestFilter.ACTIVE,
    val feedbackMessage: String? = null
) {
    val currentUser: Member?
        get() = members.find { it.id == currentUserId }

    val pendingMembers: List<Member>
        get() = members.filter { it.status == MembershipStatus.PENDING }

    val premiumMembers: List<Member>
        get() = members.filter { it.status == MembershipStatus.PREMIUM }

    val topActiveRequests: List<BloodRequest>
        get() = bloodRequests.filter { !it.isFulfilled }.take(3)

    val filteredDonors: List<Member>
        get() = members.filter { donor ->
            val matchGroup = selectedBloodGroup == "সকল" || donor.bloodGroup == selectedBloodGroup
            val matchUpazila = selectedUpazila == "সকল" || donor.upazila == selectedUpazila
            val matchQuery = searchQuery.isBlank() ||
                donor.fullName.contains(searchQuery, ignoreCase = true) ||
                donor.upazila.contains(searchQuery, ignoreCase = true) ||
                donor.phone.contains(searchQuery, ignoreCase = true)
            val matchEligible = !onlyEligibleDonors ||
                CumillaDateUtils.isEligible(donor.lastDonationEpochDays)
            matchGroup && matchUpazila && matchQuery && matchEligible
        }

    val filteredRequests: List<BloodRequest>
        get() = when (requestFilter) {
            RequestFilter.ACTIVE -> bloodRequests.filter { !it.isFulfilled }
            RequestFilter.FULFILLED -> bloodRequests.filter { it.isFulfilled }
            RequestFilter.ALL -> bloodRequests
        }
}

class RoktobinduViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RoktobinduUiState())
    val uiState: StateFlow<RoktobinduUiState> = _uiState.asStateFlow()

    fun updateBloodGroupFilter(bloodGroup: String) {
        _uiState.update { it.copy(selectedBloodGroup = bloodGroup) }
    }

    fun updateUpazilaFilter(upazila: String) {
        _uiState.update { it.copy(selectedUpazila = upazila) }
    }

    fun updateSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun toggleOnlyEligibleDonors() {
        _uiState.update { it.copy(onlyEligibleDonors = !it.onlyEligibleDonors) }
    }

    fun resetDonorFilters() {
        _uiState.update {
            it.copy(
                selectedBloodGroup = "সকল",
                selectedUpazila = "সকল",
                searchQuery = "",
                onlyEligibleDonors = false
            )
        }
    }

    fun updateRequestFilter(filter: RequestFilter) {
        _uiState.update { it.copy(requestFilter = filter) }
    }

    fun clearFeedbackMessage() {
        _uiState.update { it.copy(feedbackMessage = null) }
    }

    fun registerMember(
        fullName: String,
        phone: String,
        bloodGroup: String,
        upazila: String
    ): Boolean {
        val cleanName = fullName.trim()
        val cleanPhone = phone.trim()
        if (cleanName.isBlank() || cleanPhone.isBlank()) {
            _uiState.update {
                it.copy(feedbackMessage = "অনুগ্রহ করে নাম এবং ফোন নাম্বার লিখুন।")
            }
            return false
        }
        _uiState.update { state ->
            val existing = state.members.find { it.phone == cleanPhone }
            if (existing != null) {
                val updatedList = state.members.map {
                    if (it.id == existing.id) {
                        it.copy(fullName = cleanName, bloodGroup = bloodGroup, upazila = upazila)
                    } else {
                        it
                    }
                }
                state.copy(
                    members = updatedList,
                    currentUserId = existing.id,
                    feedbackMessage = "তথ্য হালনাগাদ হয়েছে! আইডি: ${existing.id}"
                )
            } else {
                val newId = "RBF-${1001 + state.members.size}"
                val newMember = Member(
                    id = newId,
                    fullName = cleanName,
                    phone = cleanPhone,
                    bloodGroup = bloodGroup,
                    upazila = upazila,
                    lastDonationEpochDays = null,
                    status = MembershipStatus.PENDING
                )
                state.copy(
                    members = listOf(newMember) + state.members,
                    currentUserId = newId,
                    feedbackMessage = "নিবন্ধন সফল হয়েছে! আইডি: $newId (স্ট্যাটাস: পেন্ডিং)"
                )
            }
        }
        return true
    }

    fun loginByPhone(phone: String) {
        val cleanPhone = phone.trim()
        if (cleanPhone.isBlank()) {
            _uiState.update { it.copy(feedbackMessage = "নিবন্ধিত ফোন নাম্বার লিখুন।") }
            return
        }
        val found = _uiState.value.members.find { it.phone == cleanPhone }
        _uiState.update {
            if (found != null) {
                it.copy(
                    currentUserId = found.id,
                    feedbackMessage = "স্বাগতম, ${found.fullName}!"
                )
            } else {
                it.copy(feedbackMessage = "এই নাম্বারে কোনো সদস্য পাওয়া যায়নি। নতুন নিবন্ধন করুন।")
            }
        }
    }

    fun logout() {
        _uiState.update {
            it.copy(
                currentUserId = null,
                feedbackMessage = "সফলভাবে লগআউট হয়েছে।"
            )
        }
    }

    fun approveMember(memberId: String) {
        _uiState.update { state ->
            var approvedName = ""
            val updated = state.members.map { member ->
                if (member.id == memberId) {
                    approvedName = member.fullName
                    member.copy(status = MembershipStatus.PREMIUM)
                } else {
                    member
                }
            }
            state.copy(
                members = updated,
                feedbackMessage = "$approvedName প্রিমিয়াম সদস্য হিসেবে অনুমোদিত হয়েছেন।"
            )
        }
    }

    fun updateLastDonationDate(memberId: String, epochDays: Long?) {
        _uiState.update { state ->
            val updated = state.members.map { member ->
                if (member.id == memberId) {
                    member.copy(lastDonationEpochDays = epochDays)
                } else {
                    member
                }
            }
            state.copy(
                members = updated,
                feedbackMessage = if (epochDays == null) {
                    "সর্বশেষ রক্তদানের তারিখ রিসেট করা হয়েছে।"
                } else {
                    "সর্বশেষ রক্তদানের তারিখ আপডেট করা হয়েছে।"
                }
            )
        }
    }

    fun postBloodRequest(
        patientName: String,
        bloodGroup: String,
        bagsNeeded: Int,
        hospitalName: String,
        upazila: String,
        contactNumber: String,
        urgencyNote: String
    ): Boolean {
        if (patientName.isBlank() || hospitalName.isBlank() || contactNumber.isBlank()) {
            _uiState.update {
                it.copy(feedbackMessage = "রোগীর নাম, হাসপাতালের নাম এবং যোগাযোগ নাম্বার আবশ্যক।")
            }
            return false
        }
        _uiState.update { state ->
            val newRequest = BloodRequest(
                id = "REQ-${201 + state.bloodRequests.size}",
                patientName = patientName.trim(),
                bloodGroup = bloodGroup,
                bagsNeeded = bagsNeeded.coerceIn(1, 10),
                hospitalName = hospitalName.trim(),
                upazila = upazila,
                contactNumber = contactNumber.trim(),
                urgencyNote = urgencyNote.trim().ifEmpty { "জরুরি রক্ত প্রয়োজন" },
                postedTimeLabel = "এইমাত্র",
                isFulfilled = false
            )
            state.copy(
                bloodRequests = listOf(newRequest) + state.bloodRequests,
                feedbackMessage = "জরুরি রক্তের অনুরোধ সফলভাবে প্রকাশিত হয়েছে।"
            )
        }
        return true
    }

    fun toggleRequestFulfilled(requestId: String, fulfilled: Boolean) {
        _uiState.update { state ->
            val updated = state.bloodRequests.map { req ->
                if (req.id == requestId) req.copy(isFulfilled = fulfilled) else req
            }
            state.copy(
                bloodRequests = updated,
                feedbackMessage = if (fulfilled) {
                    "অনুরোধটি 'রক্ত পাওয়া গেছে' হিসেবে চিহ্নিত হয়েছে।"
                } else {
                    "অনুরোধটি পুনরায় সক্রিয় করা হয়েছে।"
                }
            )
        }
    }

    fun registerVolunteer(
        fullName: String,
        phone: String,
        upazila: String,
        bloodGroup: String,
        roleArea: String,
        alsoRegisterAsDonor: Boolean
    ): Boolean {
        val cleanName = fullName.trim()
        val cleanPhone = phone.trim()
        if (cleanName.isBlank() || cleanPhone.isBlank()) {
            _uiState.update {
                it.copy(feedbackMessage = "স্বেচ্ছাসেবকের নাম ও ফোন নাম্বার লিখুন।")
            }
            return false
        }
        _uiState.update { state ->
            val volunteer = VolunteerApplication(
                id = "VOL-${401 + state.volunteers.size}",
                fullName = cleanName,
                phone = cleanPhone,
                upazila = upazila,
                bloodGroup = bloodGroup,
                roleArea = roleArea
            )
            val updatedMembers = if (alsoRegisterAsDonor && state.members.none { it.phone == cleanPhone }) {
                val newMember = Member(
                    id = "RBF-${1001 + state.members.size}",
                    fullName = cleanName,
                    phone = cleanPhone,
                    bloodGroup = bloodGroup,
                    upazila = upazila,
                    status = MembershipStatus.PENDING,
                    isVolunteer = true
                )
                listOf(newMember) + state.members
            } else {
                state.members
            }
            state.copy(
                volunteers = listOf(volunteer) + state.volunteers,
                members = updatedMembers,
                feedbackMessage = "স্বেচ্ছাসেবক হিসেবে আপনার নিবন্ধন সম্পন্ন হয়েছে!"
            )
        }
        return true
    }
}

@Composable
fun rememberPhoneCaller(): (String) -> Unit {
    val context = LocalContext.current
    var pendingNumber by remember { mutableStateOf<String?>(null) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        val number = pendingNumber
        pendingNumber = null
        if (!number.isNullOrBlank()) {
            placePhoneCall(context, number, directCallAllowed = granted)
        }
    }

    return { rawPhone ->
        val cleanPhone = rawPhone.replace(" ", "").trim()
        val hasPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CALL_PHONE
        ) == PackageManager.PERMISSION_GRANTED
        if (hasPermission) {
            placePhoneCall(context, cleanPhone, directCallAllowed = true)
        } else {
            pendingNumber = cleanPhone
            permissionLauncher.launch(Manifest.permission.CALL_PHONE)
        }
    }
}

private fun placePhoneCall(
    context: Context,
    phoneNumber: String,
    directCallAllowed: Boolean
) {
    val action = if (directCallAllowed) Intent.ACTION_CALL else Intent.ACTION_DIAL
    val intent = Intent(action, Uri.parse("tel:$phoneNumber")).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    runCatching { context.startActivity(intent) }.onFailure {
        val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber")).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        runCatching { context.startActivity(dialIntent) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoktobinduApp(viewModel: RoktobinduViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    var activeTab by rememberSaveable { mutableStateOf(BottomNavTab.HOME) }
    var activeOverlay by rememberSaveable { mutableStateOf(ActiveOverlay.NONE) }
    val onCallPhone = rememberPhoneCaller()

    LaunchedEffect(uiState.feedbackMessage) {
        val msg = uiState.feedbackMessage
        if (!msg.isNullOrBlank()) {
            snackbarHostState.showSnackbar(msg)
            viewModel.clearFeedbackMessage()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PureWhite),
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    Brush.linearGradient(listOf(CrimsonPrimary, CrimsonDark))
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.WaterDrop,
                                contentDescription = "রক্তবিন্দু লোগো",
                                tint = Color.White,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = CumillaData.ORG_NAME,
                                style = MaterialTheme.typography.titleLarge,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "${CumillaData.ORG_SLOGAN} • কুমিল্লা",
                                style = MaterialTheme.typography.bodySmall,
                                color = CrimsonPrimary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = { activeOverlay = ActiveOverlay.SENIOR_PANEL }) {
                        BadgedBox(
                            badge = {
                                if (uiState.pendingMembers.isNotEmpty()) {
                                    Badge(
                                        containerColor = CrimsonPrimary,
                                        contentColor = Color.White
                                    ) {
                                        Text(CumillaDateUtils.toBengaliDigits(uiState.pendingMembers.size))
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.AdminPanelSettings,
                                contentDescription = "সিনিয়র প্যানেল",
                                tint = CrimsonPrimary
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = PureWhite,
                tonalElevation = 4.dp
            ) {
                BottomNavTab.entries.forEach { tab ->
                    val selected = activeTab == tab
                    NavigationBarItem(
                        selected = selected,
                        onClick = { activeTab = tab },
                        icon = {
                            Icon(
                                imageVector = if (selected) tab.selectedIcon else tab.unselectedIcon,
                                contentDescription = tab.label
                            )
                        },
                        label = {
                            Text(
                                text = tab.label,
                                style = MaterialTheme.typography.labelSmall,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = CrimsonPrimary,
                            selectedTextColor = CrimsonPrimary,
                            indicatorColor = CrimsonSoft,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (activeTab) {
                BottomNavTab.HOME -> HomeScreen(
                    totalDonors = uiState.members.size,
                    eligibleDonorsCount = uiState.members.count {
                        CumillaDateUtils.isEligible(it.lastDonationEpochDays)
                    },
                    topActiveRequests = uiState.topActiveRequests,
                    onNavigateToFindDonor = { activeTab = BottomNavTab.FIND_DONOR },
                    onNavigateToRequests = { activeTab = BottomNavTab.REQUESTS },
                    onOpenEmergencyRequestSheet = { activeOverlay = ActiveOverlay.EMERGENCY_REQUEST },
                    onOpenDonorRegistrationSheet = { activeOverlay = ActiveOverlay.DONOR_REGISTRATION },
                    onOpenVolunteerRegistrationSheet = {
                        activeOverlay = ActiveOverlay.VOLUNTEER_REGISTRATION
                    },
                    onOpenTeamSheet = { activeOverlay = ActiveOverlay.OUR_TEAM },
                    onOpenHelplineSheet = { activeOverlay = ActiveOverlay.HELPLINE },
                    onCallPhone = onCallPhone
                )

                BottomNavTab.FIND_DONOR -> FindDonorScreen(
                    donors = uiState.filteredDonors,
                    selectedBloodGroup = uiState.selectedBloodGroup,
                    selectedUpazila = uiState.selectedUpazila,
                    searchQuery = uiState.searchQuery,
                    onlyEligible = uiState.onlyEligibleDonors,
                    onBloodGroupSelected = viewModel::updateBloodGroupFilter,
                    onUpazilaSelected = viewModel::updateUpazilaFilter,
                    onSearchQueryChanged = viewModel::updateSearchQuery,
                    onToggleOnlyEligible = viewModel::toggleOnlyEligibleDonors,
                    onResetFilters = viewModel::resetDonorFilters,
                    onCallPhone = onCallPhone
                )

                BottomNavTab.REQUESTS -> BloodRequestsScreen(
                    requests = uiState.filteredRequests,
                    activeFilter = uiState.requestFilter,
                    onFilterChanged = viewModel::updateRequestFilter,
                    onToggleFulfilled = viewModel::toggleRequestFulfilled,
                    onOpenNewRequestSheet = { activeOverlay = ActiveOverlay.EMERGENCY_REQUEST },
                    onCallPhone = onCallPhone
                )

                BottomNavTab.NOTICES -> NoticesScreen(notices = uiState.notices)

                BottomNavTab.PROFILE -> ProfileScreen(
                    currentUser = uiState.currentUser,
                    pendingMembersCount = uiState.pendingMembers.size,
                    onRegisterMember = { name, phone, bg, upazila ->
                        viewModel.registerMember(name, phone, bg, upazila)
                    },
                    onLoginByPhone = viewModel::loginByPhone,
                    onLogout = viewModel::logout,
                    onUpdateLastDonationDate = viewModel::updateLastDonationDate,
                    onOpenSeniorPanel = { activeOverlay = ActiveOverlay.SENIOR_PANEL }
                )
            }
        }
    }

    when (activeOverlay) {
        ActiveOverlay.NONE -> Unit

        ActiveOverlay.SENIOR_PANEL -> SeniorApprovalDialog(
            pendingMembers = uiState.pendingMembers,
            premiumMembers = uiState.premiumMembers,
            onApproveMember = { member -> viewModel.approveMember(member.id) },
            onDismiss = { activeOverlay = ActiveOverlay.NONE }
        )

        ActiveOverlay.EMERGENCY_REQUEST -> EmergencyRequestSheet(
            onDismiss = { activeOverlay = ActiveOverlay.NONE },
            onSubmit = { patient, bg, bags, hospital, upazila, contact, note ->
                val ok = viewModel.postBloodRequest(
                    patientName = patient,
                    bloodGroup = bg,
                    bagsNeeded = bags,
                    hospitalName = hospital,
                    upazila = upazila,
                    contactNumber = contact,
                    urgencyNote = note
                )
                if (ok) activeOverlay = ActiveOverlay.NONE
            }
        )

        ActiveOverlay.DONOR_REGISTRATION -> DonorRegistrationSheet(
            onDismiss = { activeOverlay = ActiveOverlay.NONE },
            onSubmit = { name, phone, bg, upazila ->
                val ok = viewModel.registerMember(name, phone, bg, upazila)
                if (ok) activeOverlay = ActiveOverlay.NONE
            }
        )

        ActiveOverlay.VOLUNTEER_REGISTRATION -> VolunteerRegistrationSheet(
            onDismiss = { activeOverlay = ActiveOverlay.NONE },
            onSubmit = { name, phone, upazila, bg, roleArea, alsoAsDonor ->
                val ok = viewModel.registerVolunteer(
                    fullName = name,
                    phone = phone,
                    upazila = upazila,
                    bloodGroup = bg,
                    roleArea = roleArea,
                    alsoRegisterAsDonor = alsoAsDonor
                )
                if (ok) activeOverlay = ActiveOverlay.NONE
            }
        )

        ActiveOverlay.OUR_TEAM -> TeamSheet(
            onDismiss = { activeOverlay = ActiveOverlay.NONE },
            onCallPhone = onCallPhone
        )

        ActiveOverlay.HELPLINE -> HelplineSheet(
            onDismiss = { activeOverlay = ActiveOverlay.NONE },
            onCallPhone = onCallPhone
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SeniorApprovalDialog(
    pendingMembers: List<Member>,
    premiumMembers: List<Member>,
    onApproveMember: (Member) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        BackHandler(onBack = onDismiss)
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = PureWhite),
                    navigationIcon = {
                        IconButton(onClick = onDismiss) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "ফিরে যান"
                            )
                        }
                    },
                    title = {
                        Column {
                            Text(
                                text = "সিনিয়র প্যানেল (সদস্য অনুমোদন)",
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = "পেন্ডিং আবেদন যাচাই করে প্রিমিয়াম সদস্য অনুমোদন দিন",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                )

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            SeniorSummaryCard(
                                title = "অপেক্ষমাণ (পেন্ডিং)",
                                count = CumillaDateUtils.toBengaliDigits(pendingMembers.size),
                                bg = AmberContainer,
                                fg = AmberWaiting,
                                modifier = Modifier.weight(1f)
                            )
                            SeniorSummaryCard(
                                title = "অনুমোদিত প্রিমিয়াম",
                                count = CumillaDateUtils.toBengaliDigits(premiumMembers.size),
                                bg = EmeraldContainer,
                                fg = EmeraldVerified,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                    item {
                        Text(
                            text = "অনুমোদনের অপেক্ষায় (${CumillaDateUtils.toBengaliDigits(pendingMembers.size)})",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    if (pendingMembers.isEmpty()) {
                        item {
                            Surface(
                                shape = RoundedCornerShape(16.dp),
                                color = PureWhite,
                                border = BorderStroke(1.dp, OutlineSubtle),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.CheckCircle,
                                        contentDescription = null,
                                        tint = EmeraldVerified
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = "সব সদস্যের আবেদন অনুমোদিত হয়েছে!",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }
                    } else {
                        items(pendingMembers, key = { it.id }) { member ->
                            Card(
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = PureWhite),
                                border = BorderStroke(1.dp, AmberWaiting.copy(alpha = 0.35f)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(14.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        BloodGroupBadge(bloodGroup = member.bloodGroup, size = 46)
                                        Spacer(modifier = Modifier.width(12.dp))
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = member.fullName,
                                                style = MaterialTheme.typography.titleMedium
                                            )
                                            Text(
                                                text = "আইডি: ${member.id} • ${member.upazila}, কুমিল্লা",
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                            Text(
                                                text = "ফোন: ${member.phone}",
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                        MembershipStatusBadge(status = member.status)
                                    }
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Button(
                                        onClick = { onApproveMember(member) },
                                        modifier = Modifier.fillMaxWidth(),
                                        shape = RoundedCornerShape(12.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = EmeraldVerified,
                                            contentColor = Color.White
                                        )
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Verified,
                                            contentDescription = null,
                                            modifier = Modifier.size(18.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text("অনুমোদন করুন (Approve)")
                                    }
                                }
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "প্রিমিয়াম যাচাইকৃত সদস্যবৃন্দ (${CumillaDateUtils.toBengaliDigits(premiumMembers.size)})",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    items(premiumMembers, key = { "prem_${it.id}" }) { member ->
                        Card(
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = PureWhite),
                            border = BorderStroke(1.dp, OutlineSubtle),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                BloodGroupBadge(bloodGroup = member.bloodGroup, size = 42)
                                Spacer(modifier = Modifier.width(10.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = member.fullName,
                                            style = MaterialTheme.typography.titleSmall,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Icon(
                                            imageVector = Icons.Filled.Verified,
                                            contentDescription = "প্রিমিয়াম",
                                            tint = EmeraldVerified,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                    Text(
                                        text = "${member.id} • ${member.upazila} • ${member.phone}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SeniorSummaryCard(
    title: String,
    count: String,
    bg: Color,
    fg: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = bg,
        border = BorderStroke(1.dp, fg.copy(alpha = 0.25f))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = count,
                style = MaterialTheme.typography.headlineMedium,
                color = fg,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EmergencyRequestSheet(
    onDismiss: () -> Unit,
    onSubmit: (String, String, Int, String, String, String, String) -> Unit
) {
    var patientName by remember { mutableStateOf("") }
    var bloodGroup by remember { mutableStateOf(CumillaData.BLOOD_GROUPS.first()) }
    var bagsNeeded by remember { mutableIntStateOf(1) }
    var hospitalName by remember { mutableStateOf("") }
    var upazila by remember { mutableStateOf(CumillaData.CUMILLA_UPAZILAS.first()) }
    var contactNumber by remember { mutableStateOf("") }
    var urgencyNote by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = PureWhite
    ) {
        BackHandler(onBack = onDismiss)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 8.dp)
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Bloodtype, contentDescription = null, tint = CrimsonPrimary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("জরুরি রক্তের অনুরোধ", style = MaterialTheme.typography.titleLarge)
                }
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Filled.Close, contentDescription = "বন্ধ করুন")
                }
            }

            OutlinedTextField(
                value = patientName,
                onValueChange = { patientName = it },
                label = { Text("রোগীর নাম") },
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                DropdownSelectorField(
                    label = "রক্তের গ্রুপ",
                    selectedValue = bloodGroup,
                    options = CumillaData.BLOOD_GROUPS,
                    onOptionSelected = { bloodGroup = it },
                    modifier = Modifier.weight(0.55f)
                )
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(1.dp, OutlineSubtle),
                    color = PureWhite,
                    modifier = Modifier.weight(0.45f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${CumillaDateUtils.toBengaliDigits(bagsNeeded)} ব্যাগ",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Row {
                            OutlinedButton(
                                onClick = { if (bagsNeeded > 1) bagsNeeded-- },
                                contentPadding = PaddingValues(0.dp),
                                modifier = Modifier.size(34.dp)
                            ) { Text("-") }
                            Spacer(modifier = Modifier.width(4.dp))
                            OutlinedButton(
                                onClick = { if (bagsNeeded < 8) bagsNeeded++ },
                                contentPadding = PaddingValues(0.dp),
                                modifier = Modifier.size(34.dp)
                            ) { Text("+") }
                        }
                    }
                }
            }

            OutlinedTextField(
                value = hospitalName,
                onValueChange = { hospitalName = it },
                label = { Text("হাসপাতালের নাম (কুমিল্লা)") },
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            )

            DropdownSelectorField(
                label = "উপজেলা (কুমিল্লা)",
                selectedValue = upazila,
                options = CumillaData.CUMILLA_UPAZILAS,
                onOptionSelected = { upazila = it },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = contactNumber,
                onValueChange = { contactNumber = it },
                label = { Text("যোগাযোগের ফোন নাম্বার") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = urgencyNote,
                onValueChange = { urgencyNote = it },
                label = { Text("রোগীর সমস্যা ও জরুরি নোট") },
                minLines = 2,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    onSubmit(
                        patientName,
                        bloodGroup,
                        bagsNeeded,
                        hospitalName,
                        upazila,
                        contactNumber,
                        urgencyNote
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CrimsonPrimary,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(vertical = 13.dp)
            ) {
                Text("অনুরোধ প্রকাশ করুন", style = MaterialTheme.typography.labelLarge)
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DonorRegistrationSheet(
    onDismiss: () -> Unit,
    onSubmit: (String, String, String, String) -> Unit
) {
    var fullName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var bloodGroup by remember { mutableStateOf(CumillaData.BLOOD_GROUPS.first()) }
    var upazila by remember { mutableStateOf(CumillaData.CUMILLA_UPAZILAS.first()) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = PureWhite
    ) {
        BackHandler(onBack = onDismiss)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 8.dp)
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("রক্তদাতা নিবন্ধন ফর্ম", style = MaterialTheme.typography.titleLarge)
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Filled.Close, contentDescription = "বন্ধ করুন")
                }
            }
            Text(
                text = "নিবন্ধনের পর আপনার সদস্যপদ 'পেন্ডিং' থাকবে এবং সিনিয়র প্যানেল থেকে অনুমোদনের পর প্রিমিয়াম ব্যাজ পাবেন।",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("পূর্ণ নাম") },
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("ফোন নাম্বার") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            )
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
                    label = "উপজেলা",
                    selectedValue = upazila,
                    options = CumillaData.CUMILLA_UPAZILAS,
                    onOptionSelected = { upazila = it },
                    modifier = Modifier.weight(0.55f)
                )
            }
            Button(
                onClick = { onSubmit(fullName, phone, bloodGroup, upazila) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CrimsonPrimary,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(vertical = 13.dp)
            ) {
                Text("নিবন্ধন সম্পন্ন করুন", style = MaterialTheme.typography.labelLarge)
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun VolunteerRegistrationSheet(
    onDismiss: () -> Unit,
    onSubmit: (String, String, String, String, String, Boolean) -> Unit
) {
    var fullName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var upazila by remember { mutableStateOf(CumillaData.CUMILLA_UPAZILAS.first()) }
    var bloodGroup by remember { mutableStateOf(CumillaData.BLOOD_GROUPS.first()) }
    var roleArea by remember { mutableStateOf(CumillaData.VOLUNTEER_ROLES.first()) }
    var alsoAsDonor by remember { mutableStateOf(true) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = PureWhite
    ) {
        BackHandler(onBack = onDismiss)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 8.dp)
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.VolunteerActivism,
                        contentDescription = null,
                        tint = CrimsonPrimary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("স্বেচ্ছাসেবক নিবন্ধন", style = MaterialTheme.typography.titleLarge)
                }
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Filled.Close, contentDescription = "বন্ধ করুন")
                }
            }

            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("স্বেচ্ছাসেবকের নাম") },
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("মোবাইল নাম্বার") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                DropdownSelectorField(
                    label = "রক্তের গ্রুপ",
                    selectedValue = bloodGroup,
                    options = CumillaData.BLOOD_GROUPS,
                    onOptionSelected = { bloodGroup = it },
                    modifier = Modifier.weight(0.42f)
                )
                DropdownSelectorField(
                    label = "উপজেলা",
                    selectedValue = upazila,
                    options = CumillaData.CUMILLA_UPAZILAS,
                    onOptionSelected = { upazila = it },
                    modifier = Modifier.weight(0.58f)
                )
            }
            DropdownSelectorField(
                label = "আগ্রহী কাজের ক্ষেত্র",
                selectedValue = roleArea,
                options = CumillaData.VOLUNTEER_ROLES,
                onOptionSelected = { roleArea = it },
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = alsoAsDonor,
                    onCheckedChange = { alsoAsDonor = it },
                    colors = CheckboxDefaults.colors(checkedColor = CrimsonPrimary)
                )
                Text(
                    text = "আমাকে রক্তদাতা তালিকায়ও যুক্ত করুন",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Button(
                onClick = {
                    onSubmit(fullName, phone, upazila, bloodGroup, roleArea, alsoAsDonor)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CrimsonPrimary,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(vertical = 13.dp)
            ) {
                Text("স্বেচ্ছাসেবক হিসেবে যোগ দিন", style = MaterialTheme.typography.labelLarge)
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TeamSheet(
    onDismiss: () -> Unit,
    onCallPhone: (String) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        BackHandler(onBack = onDismiss)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .navigationBarsPadding()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("আমাদের টিম", style = MaterialTheme.typography.titleLarge)
                    Text(
                        text = "প্রতিষ্ঠাতা ও কার্যনির্বাহী পরিষদ",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Filled.Close, contentDescription = "বন্ধ করুন")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                items(CumillaData.FOUNDATION_TEAM, key = { it.id }) { member ->
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = PureWhite),
                        border = BorderStroke(1.dp, OutlineSubtle),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            BloodGroupBadge(bloodGroup = member.bloodGroup, size = 46)
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Surface(
                                    shape = RoundedCornerShape(50),
                                    color = CrimsonSoft
                                ) {
                                    Text(
                                        text = member.badgeTitle,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = CrimsonPrimary,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(member.name, style = MaterialTheme.typography.titleMedium)
                                Text(
                                    text = member.role,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = "${member.upazila}, কুমিল্লা • ${member.phone}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            IconButton(
                                onClick = { onCallPhone(member.phone) },
                                modifier = Modifier.size(48.dp)
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = EmeraldContainer
                                ) {
                                    Box(modifier = Modifier.padding(10.dp)) {
                                        Icon(
                                            imageVector = Icons.Filled.Call,
                                            contentDescription = "কল করুন",
                                            tint = EmeraldVerified,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HelplineSheet(
    onDismiss: () -> Unit,
    onCallPhone: (String) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        BackHandler(onBack = onDismiss)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .navigationBarsPadding()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("জরুরি হেল্পলাইন (কুমিল্লা)", style = MaterialTheme.typography.titleLarge)
                    Text(
                        text = "ব্লাড ব্যাংক, হাসপাতাল ও অ্যাম্বুলেন্স সেবা",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Filled.Close, contentDescription = "বন্ধ করুন")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                items(CumillaData.EMERGENCY_HELPLINES, key = { it.id }) { contact ->
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = PureWhite),
                        border = BorderStroke(1.dp, OutlineSubtle),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Surface(shape = RoundedCornerShape(50), color = CrimsonSoft) {
                                    Text(
                                        text = contact.category,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = CrimsonPrimary,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                    )
                                }
                                Text(
                                    text = contact.availableHours,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = EmeraldVerified,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Filled.LocalHospital,
                                    contentDescription = null,
                                    tint = CrimsonPrimary,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(contact.title, style = MaterialTheme.typography.titleMedium)
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Filled.LocationOn,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = contact.subtitle,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Button(
                                onClick = { onCallPhone(contact.phone) },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = CrimsonPrimary,
                                    contentColor = Color.White
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Call,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("কল করুন (${contact.phone})")
                            }
                        }
                    }
                }
            }
        }
    }
}
