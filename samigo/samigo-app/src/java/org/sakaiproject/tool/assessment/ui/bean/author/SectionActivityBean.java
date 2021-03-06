/**
 * Copyright (c) 2005-2016 The Apereo Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *             http://opensource.org/licenses/ecl2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sakaiproject.tool.assessment.ui.bean.author;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sakaiproject.user.cover.UserDirectoryService;

public class SectionActivityBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static Logger log = LoggerFactory.getLogger(SectionActivityBean.class);

    private List displayNamesList;
    private String selectedUser;
    private List sectionActivityDataList;
    private String sortType="assessmentName";
    private boolean sortAscending = true;

    public List getDisplayNamesList() {
        return displayNamesList;
    }

    public void setDisplayNamesList(List displayNamesList) {
        this.displayNamesList = displayNamesList;
    }

    public String getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(String selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List getSectionActivityDataList() {
        return sectionActivityDataList;
    }
   

    public void setSectionActivityDataList(List sectionActivityDataList) {
        this.sectionActivityDataList = sectionActivityDataList;
    }

    public String getSelectedUserDisplayName() {
        String displayName="";
        try {      
            displayName = UserDirectoryService.getUser(selectedUser).getDisplayName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return displayName ;
    }
   
    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public boolean isSortAscending() {
        return sortAscending;
    }

    public void setSortAscending(boolean sortAscending) {
        this.sortAscending = sortAscending;
    }  
   
}
