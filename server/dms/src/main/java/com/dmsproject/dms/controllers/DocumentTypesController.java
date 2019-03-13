package com.dmsproject.dms.controllers;

import com.dmsproject.dms.Constants;
import com.dmsproject.dms.dao.DocTypesDAO;
import com.dmsproject.dms.dto.DocTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@CrossOrigin(origins = Constants.REACT_URL)
public class DocumentTypesController {

    @Autowired
    private DocTypesDAO docTypesDAO;

    @RequestMapping(value = "/documentTypes/getTemplate", method = RequestMethod.GET, produces = "application/json")
    public DocTypes getDocTemplate(@RequestParam(name = "id") Integer id) {
        return docTypesDAO.getDocTemplateByType(id);
    }

    @RequestMapping(value = "/documentTypes/get", method = RequestMethod.GET, produces = "application/json")
    public List<DocTypes> getDocTypes() {
        return docTypesDAO.getDocTypes();
    }


    @RequestMapping(value = "/documentTemplate/add", method = RequestMethod.POST)
    public Boolean add(@RequestParam(name = "description") String description,
                       @RequestParam(name = "template") String template) {
        DocTypes docTypes = new DocTypes();
        docTypes.setDescription(description);
        docTypes.setTemplate(template);

        return docTypesDAO.addDocTemplate(docTypes);
    }

    @RequestMapping(value = "/documentTemplate/edit", method = RequestMethod.POST)
    public void edit(@RequestParam(name = "id") Integer id,
            @RequestParam(name = "description") String description,
                     @RequestParam(name = "template") String template) {
        DocTypes docTypes = new DocTypes();
        docTypes.setId(id);
        docTypes.setDescription(description);
        docTypes.setTemplate(template);

        docTypesDAO.editDocTemplate(docTypes);
    }

    @RequestMapping(value = "/documentTemplate/delete", method = RequestMethod.DELETE)
    public void delete(@RequestParam(name = "docTypeId") Integer docTypeId) {
        docTypesDAO.deleteTemplate(docTypeId);
    }
}
