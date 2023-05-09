package com.example.shurtiandroidproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shurtiandroidproject.adapter.BestBooksForAllTimeAdapter
import com.example.shurtiandroidproject.adapter.BestFromChildrenAdapter
import com.example.shurtiandroidproject.adapter.BestFromCrimeAndThrillerAdapter
import com.example.shurtiandroidproject.adapter.BestFromLiteratureAndFictionAdapter
import com.example.shurtiandroidproject.adapter.BestFromNonFictionAdapter
import com.example.shurtiandroidproject.adapter.BestFromYoungAuditAdapter
import com.example.shurtiandroidproject.adapter.BestOfCeceliaAhernAdapter
import com.example.shurtiandroidproject.adapter.BestSellerOfHealthBookAdapter
import com.example.shurtiandroidproject.adapter.FeministsAdapter
import com.example.shurtiandroidproject.adapter.IncredibleIndiaAdapter
import com.example.shurtiandroidproject.adapter.LeadershipBookAdapter
import com.example.shurtiandroidproject.adapter.NewArrivalAdapter
import com.example.shurtiandroidproject.adapter.PoliticalMarvelsAdapter
import com.example.shurtiandroidproject.adapter.PopularBooksAdapter
import com.example.shurtiandroidproject.adapter.RvCatAdapter
import com.example.shurtiandroidproject.databinding.FragmentHomeBinding
import com.example.shurtiandroidproject.viewmodel.MainVM


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