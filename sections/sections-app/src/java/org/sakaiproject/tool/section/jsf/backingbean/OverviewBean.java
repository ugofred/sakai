/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2005, 2006 The Regents of the University of California and The Regents of the University of Michigan
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 * 
 *      http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *
 **********************************************************************************/
package org.sakaiproject.tool.section.jsf.backingbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.tool.section.decorator.SectionDecorator;
import org.sakaiproject.tool.section.jsf.JsfUtil;

/**
 * Controls the overview page.
 *
 * @author <a href="mailto:jholtzman@berkeley.edu">Josh Holtzman</a>
 *
 */
public class OverviewBean extends FilteredSectionListingBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(OverviewBean.class);

	private boolean externallyManaged;

	private List<SectionDecorator> sectionsToDelete;

	public void init() {
		super.init();
		// Determine whether this course is externally managed
		externallyManaged = getSectionManager().isExternallyManaged(getCourse().getUuid());

	}

	protected Comparator<SectionDecorator> getComparator() {
		String sortColumn = getPrefs().getOverviewSortColumn();
		boolean sortAscending = getPrefs().isOverviewSortAscending();

		if(sortColumn.equals("title")) {
			return SectionDecorator.getTitleComparator(sortAscending);
		} else if(sortColumn.equals("managers")) {
			return SectionDecorator.getManagersComparator(sortAscending);
		} else if(sortColumn.equals("totalEnrollments")) {
			return SectionDecorator.getEnrollmentsComparator(sortAscending, false);
		} else if(sortColumn.equals("available")) {
			return SectionDecorator.getEnrollmentsComparator(sortAscending, true);
		} else if(sortColumn.equals("meetingDays")) {
			return SectionDecorator.getDayComparator(sortAscending);
		} else if(sortColumn.equals("meetingTimes")) {
			return SectionDecorator.getDayComparator(sortAscending);
		} else if(sortColumn.equals("location")) {
			return SectionDecorator.getLocationComparator(sortAscending);
		}
		log.error("Invalid sort specified.");
		return null;
	}

	public String confirmDelete() {
		sectionsToDelete = new ArrayList<SectionDecorator>();
		for(Iterator<SectionDecorator> iter = sections.iterator(); iter.hasNext();) {
			SectionDecorator decoratedSection = iter.next();
			if(decoratedSection.isFlaggedForRemoval()) {
				sectionsToDelete.add(decoratedSection);
			}
		}
		if(sectionsToDelete.isEmpty()) {
			JsfUtil.addErrorMessage(JsfUtil.getLocalizedMessage("overview_delete_section_choose"));
			return null; // Don't go anywhere
		} else {
			return "deleteSections";
		}
	}
	
	public String deleteSections() {
		for(Iterator iter = sectionsToDelete.iterator(); iter.hasNext();) {
			SectionDecorator decoratedSection = (SectionDecorator)iter.next();
			getSectionManager().disbandSection(decoratedSection.getUuid());
		}
		JsfUtil.addRedirectSafeInfoMessage(JsfUtil.getLocalizedMessage("overview_delete_section_success"));
		return "overview";
	}
	public boolean isDeleteRendered() {
		return (!externallyManaged) && sections.size() > 0 && isSectionManagementEnabled();
	}

	public boolean isExternallyManaged() {
		return externallyManaged;
	}

	public List getSectionsToDelete() {
		return sectionsToDelete;
	}
}
