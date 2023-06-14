package com.lock.the.box.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lock.the.box.adapter.BestBooksForAllTimeAdapter
import com.lock.the.box.adapter.BestFromChildrenAdapter
import com.lock.the.box.adapter.BestFromCrimeAndThrillerAdapter
import com.lock.the.box.adapter.BestFromLiteratureAndFictionAdapter
import com.lock.the.box.adapter.BestFromNonFictionAdapter
import com.lock.the.box.adapter.BestFromYoungAuditAdapter
import com.lock.the.box.adapter.BestOfCeceliaAhernAdapter
import com.lock.the.box.adapter.BestSellerOfHealthBookAdapter
import com.lock.the.box.adapter.FeministsAdapter
import com.lock.the.box.adapter.IncredibleIndiaAdapter
import com.lock.the.box.adapter.LeadershipBookAdapter
import com.lock.the.box.adapter.NewArrivalAdapter
import com.lock.the.box.adapter.PoliticalMarvelsAdapter
import com.lock.the.box.adapter.RvCatAdapter
import com.lock.the.box.databinding.FragmentHomeBinding
import com.lock.the.box.viewmodel.MainVM


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    lateinit var viewModel: MainVM
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

//        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
        }

        initializeView()
        
        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initializeView() {
        binding.rvCat.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val adapter = RvCatAdapter()
        binding.rvCat.adapter = adapter

        binding.rvBestBookAllTime.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val bestBooksForAllTimeAdapter = BestBooksForAllTimeAdapter()
        binding.rvBestBookAllTime.adapter = bestBooksForAllTimeAdapter

        //

        binding.rvBestSellerHealthBook.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val bestSellerOfHealthBookAdapter = BestSellerOfHealthBookAdapter()
        binding.rvBestSellerHealthBook.adapter = bestSellerOfHealthBookAdapter

        binding.rvLeadershipBook.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val leadershipBookAdapter = LeadershipBookAdapter()
        binding.rvLeadershipBook.adapter = leadershipBookAdapter

        binding.rvBestOfCeceliaAhem.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val bestOfCeceliaAhernAdapter = BestOfCeceliaAhernAdapter()
        binding.rvBestOfCeceliaAhem.adapter = bestOfCeceliaAhernAdapter

        binding.rvNewArrival.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val newArrivalAdapter = NewArrivalAdapter()
        binding.rvNewArrival.adapter = newArrivalAdapter

        binding.rvFeminists.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val feministsAdapter = FeministsAdapter()
        binding.rvFeminists.adapter = feministsAdapter

        binding.rvPoliticalMarvels.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val politicalMarvelsAdapter = PoliticalMarvelsAdapter()
        binding.rvPoliticalMarvels.adapter = politicalMarvelsAdapter

        binding.rvIncredibleIndia.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val incredibleIndiaAdapter = IncredibleIndiaAdapter()
        binding.rvIncredibleIndia.adapter = incredibleIndiaAdapter

        binding.rvBestForCrimeAndThriller.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val bestFromCrimeAndThrillerAdapter = BestFromCrimeAndThrillerAdapter()
        binding.rvBestForCrimeAndThriller.adapter = bestFromCrimeAndThrillerAdapter

        binding.rvBestFromYoungAudit.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val bestFromYoungAuditAdapter = BestFromYoungAuditAdapter()
        binding.rvBestFromYoungAudit.adapter = bestFromYoungAuditAdapter

        binding.rvBestFromNonFiction.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val bestFromNonFictionAdapter = BestFromNonFictionAdapter()
        binding.rvBestFromNonFiction.adapter = bestFromNonFictionAdapter

        binding.rvBestFromChildren.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val bestFromChildrenAdapter = BestFromChildrenAdapter()
        binding.rvBestFromChildren.adapter = bestFromChildrenAdapter

        binding.rvBestFromLiteratureAndFiction.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val bestFromLiteratureAndFictionAdapter = BestFromLiteratureAndFictionAdapter()
        binding.rvBestFromLiteratureAndFiction.adapter = bestFromLiteratureAndFictionAdapter

    }

}