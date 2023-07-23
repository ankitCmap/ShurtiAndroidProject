package com.lock.the.box.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
import com.lock.the.box.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    private val userId:String = "12578"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.homeViewModel = homeViewModel
        binding.lifecycleOwner = this

        initializeView()
        val hashMap: HashMap<String, Any> = HashMap() //define empty hashmap
        hashMap.put("user_id",userId)
        homeViewModel.homeResponse(hashMap)
        setObserver()

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeView() {
        binding.rvCat.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val adapter = RvCatAdapter()
        binding.rvCat.adapter = adapter

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

    private fun setObserver() {
        homeViewModel.storeWiseProduct.observe(viewLifecycleOwner) {
            //if (it.status==1) {
               /* BasePreferencesManager.putBoolean(BasePreferencesManager.IS_LOGIN,true)
                Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()*/

                binding.rvBestBookAllTime.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                val bestBooksForAllTimeAdapter = BestBooksForAllTimeAdapter(it,context)
                binding.rvBestBookAllTime.adapter = bestBooksForAllTimeAdapter

            }
        //}
    }

}