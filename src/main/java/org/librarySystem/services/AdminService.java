package org.librarySystem.services;


import org.librarySystem.model.Admin;
import org.librarySystem.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    public void saveAdmin(Admin adminIncoming) {
        Admin admin = new Admin();
        admin.setName(adminIncoming.getName());
        admin.setPassword(adminIncoming.getPassword());
        admin.setEmail(adminIncoming.getEmail());
        admin.setPhoneNumber(adminIncoming.getPhoneNumber());
        adminRepository.save(admin);
    }



}
