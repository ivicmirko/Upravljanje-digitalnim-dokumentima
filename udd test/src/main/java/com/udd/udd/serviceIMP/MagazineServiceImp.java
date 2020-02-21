package com.udd.udd.serviceIMP;

import com.udd.udd.dto.NewMagazineDTO;
import com.udd.udd.model.Editor;
import com.udd.udd.model.Magazine;
import com.udd.udd.model.ScienceArea;
import com.udd.udd.model.SystemUser;
import com.udd.udd.repository.EditorRepository;
import com.udd.udd.repository.MagazineRepository;
import com.udd.udd.repository.ScienceAreaRepository;
import com.udd.udd.repository.SystemUserRepository;
import com.udd.udd.service.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MagazineServiceImp implements MagazineService {

	@Autowired
	MagazineRepository magazineRepository;

	@Autowired
	SystemUserRepository systemUserRepository;

	@Autowired
	ScienceAreaRepository scienceAreaRepository;

	@Autowired
	EditorRepository editorRepository;

	@Override
	public Magazine saveMagazine(Magazine magazine) {
		return this.magazineRepository.save(magazine);
	}

	@Override
	public Magazine findOneByName(String name) {
		return this.magazineRepository.findOneByName(name);
	}

	@Override
	public Magazine findOneById(Long id) {
		return this.magazineRepository.findOneById(id);
	}

	@Override
	public List<Magazine> findAll() {
		return this.magazineRepository.findAll();
	}

	@Override
	public boolean checkNameUniqueness(String name) {
		boolean retval=true;
		Magazine magazine=null;
		magazine=this.magazineRepository.findOneByName(name);
		if(magazine != null || name==null || name.equals("")){
			retval=false;
		}
		return retval;
	}

	@Override
	public boolean checkISSNUniqueness(String issn) {
		boolean retval=true;
//		Magazine magazine=null;
//		magazine=this.magazineRepository.findOneBy(name);
//		if(magazine != null){
//			retval=false;
//		}
		return retval;
	}

	@Override
	public Magazine addNewMagazine(NewMagazineDTO newMagazine) {
		Magazine magazine=new Magazine();
		magazine.setActive(false);
		magazine.setName(newMagazine.getName());
		magazine.setIssn(newMagazine.getIssn());
		magazine.setStatus("request");
		Editor editor=this.editorRepository.findOneByUsername(newMagazine.getUsername());
		//SystemUser systemUser=this.systemUserRepository.findByUsername(newMagazine.getUsername());
		if(editor == null){
			System.out.println("Nije nasao urednika");
			return null;
		}
		magazine.setMainEditor(editor);

		for(int i=0; i<newMagazine.getScienceAreas().size();i++){
			ScienceArea scienceArea=this.scienceAreaRepository.findOneById(Long.parseLong(newMagazine.getScienceAreas().get(i)));
			if(scienceArea!=null){
				magazine.getScienceAreas().add(scienceArea);
			}
		}

		if(newMagazine.getMembershipType().equals("1")){
			magazine.setOpenAccess(true);
		}else if(newMagazine.getMembershipType().equals("2")){
			magazine.setOpenAccess(false);
		}

		magazine=this.saveMagazine(magazine);
		return magazine;
	}

	@Override
	public Magazine addEditorialBoard(NewMagazineDTO magazineDTO) {


		Magazine magazine=this.magazineRepository.findOneById(magazineDTO.getId());
		//dodavanje urednika
		if(magazine==null){
			return null;
		}

		for(String editorId:magazineDTO.getEditors()){
			Editor editor=this.editorRepository.findOneById(Long.parseLong(editorId));
			if(editor != null){
				magazine.getEditors().add(editor);
				editor.setMagazine(magazine);
				editorRepository.save(editor);
			}
		}

		for(String reviewerId:magazineDTO.getReviewers()){
			SystemUser systemUser=this.systemUserRepository.findOneById(Long.parseLong(reviewerId));
			if(systemUser != null){
				magazine.getReviewes().add(systemUser);
			}
		}

		magazine=this.saveMagazine(magazine);
		return magazine;
	}

	@Override
	public Set<Magazine> findMagazinesByStatus(String status) {
		return this.magazineRepository.findAllByStatus(status);
	}

	@Override
	public Magazine getMagazineByMainEditor(Editor editor) {
		return this.magazineRepository.getMagazineByMainEditor(editor);
	}

	@Override
	public Magazine getMagazineByEditor(Editor editor) {
		return this.magazineRepository.findByEditorsContaining(editor);
	}
}
