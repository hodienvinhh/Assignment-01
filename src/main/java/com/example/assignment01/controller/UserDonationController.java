package com.example.assignment01.controller;

import com.example.assignment01.dto.UserLoginDTO;
import com.example.assignment01.entity.Donation;
import com.example.assignment01.entity.UserDonation;
import com.example.assignment01.service.IDonationService;
import com.example.assignment01.service.IUserDonationService;
import com.example.assignment01.utils.ContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
public class UserDonationController {
    @Autowired
    private IUserDonationService userDonationService;

    @Autowired
    private IDonationService donationService;

    @Autowired
    private ContextUtils contextUtils;


    @GetMapping("/admin/detailDonation")
    public String getUserDonationsByDonationId(@RequestParam int donationId, ModelMap modelMap) {
        UserLoginDTO userLoginDTO = (UserLoginDTO) contextUtils.getUserLogin();
        if (Objects.isNull(userLoginDTO)){
            modelMap.addAttribute("userLogin", "null");
        }else {
            modelMap.addAttribute("userLogin" , userLoginDTO);
        }

        List<UserDonation> userDonations = userDonationService.findByDonationId(donationId);
        modelMap.addAttribute("userDonationList", userDonations);// ds nhung chi tiet donate
        Donation donation = donationService.getDonationById(donationId);
        modelMap.addAttribute("donation", donation);
        return "/admin/detail";
    }
    @GetMapping("/detail")
    public String getDetailUserDonationsByDonationId(@RequestParam int donationId, ModelMap modelMap) {
        List<UserDonation> userDonations = userDonationService.findByDonationId(donationId);
        modelMap.addAttribute("userDonationList", userDonations);// ds nhung chi tiet donate
        Donation donation = donationService.getDonationById(donationId);
        modelMap.addAttribute("donation", donation);
        return "public/detail";
    }


    @PostMapping("/admin/update-donate")
    public String updateUserDonationDetail(@RequestParam int status, @RequestParam int id, @RequestParam int donationId){
        userDonationService.updateUserDonationDetail(status, id);
        return "redirect:/admin/detailDonation?donationId="+donationId;
    }
}
