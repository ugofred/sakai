/**
 * Copyright (c) 2003-2012 The Apereo Foundation
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
package org.sakaiproject.site.tool.helper.managegroupsectionrole.rsf;

import uk.org.ponder.rsf.viewstate.SimpleViewParameters;

public class CreateJoinableGroupViewParameters  extends SimpleViewParameters{
	public String id;

    public CreateJoinableGroupViewParameters(String id) {
		super();
		this.id = id;
	}
    
    public CreateJoinableGroupViewParameters() {
		super();
	}
    
	public CreateJoinableGroupViewParameters(String viewId, String id) {
		this.id= id;
		this.viewID = viewId;
	}
    
    public String getId()
    {
    	return id;
    }

	public void setId(String id) {
		this.id = id;
	}
}
