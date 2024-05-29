package org.librarySystem.services;


import org.librarySystem.model.Members;
import org.librarySystem.repository.MembersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembersService {
    private final MembersRepository membersRepository;

    public MembersService(MembersRepository membersRepository) {
        this.membersRepository = membersRepository;
    }
    public void saveMembers(Members membersIncoming){
        Members members = new Members();
        members.setFullName(membersIncoming.getFullName());
        members.setAddress(membersIncoming.getAddress());
        members.setEmail(membersIncoming.getEmail());
        members.setPhoneNumber(membersIncoming.getPhoneNumber());
        members.setAge(membersIncoming.getAge());
        members.setIdNumber(membersIncoming.getIdNumber());
        membersRepository.save(members);
    }
    public List<Members> retrieveMembers(){

        return membersRepository.findAll();
    }
    public Members findMemberByEmail(String email) {
        return membersRepository.findByEmail(email);
    }

    public void removeMembers(String email){
        Members member = membersRepository.findByEmail(email);

        if (member != null) {
            // Remove the member from the database
            membersRepository.delete(member);
    }}
}
