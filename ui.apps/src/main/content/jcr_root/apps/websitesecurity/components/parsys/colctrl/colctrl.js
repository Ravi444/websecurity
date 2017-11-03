/*******************************************************************************
 * Copyright 2016 Adobe Systems Incorporated
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

/**
 * Column control sightly foundation component JS backing script
 */
"use strict";
var global = this;
use(["/libs/wcm/foundation/components/utils/AuthoringUtils.js"], function (AuthoringUtils) {
    
    var CONST = {
        COL_CONTROL_TYPE_PROPERTY: "controlType",
        COL_CONTROL_LAYOUT_PROPERTY: "layout",
        COL_CONTROL_DEFAULT_LAYOUT: "1;cq-colctrl-default",
        
        START_PAR_COLUMN_TYPE: "start",
        END_PAR_COLUMN_TYPE: "end"
    };
    
    var _retrieveAttribute = function (attrName, defaultValue) {
        var value = defaultValue;
        if (global.request
                && global.request.getAttribute
                && global.request.getAttribute(attrName)) {
            value = global.request.getAttribute(attrName);
        }
        
        return value;
    }
    
    var _getEditContext = function () {
        var editContext = undefined;
        var componentContext = _retrieveAttribute("com.day.cq.wcm.componentcontext");
        if (componentContext) {
            editContext = componentContext.getEditContext();
        }
        
        return editContext;
    }
    
    var _getEditConfig = function () {
        var editConfig = undefined;
        var editContext = _getEditContext();
        if (editContext) {
            editConfig = editContext.getEditConfig();
        }
        
        return editConfig;
    }
    
    var editConfig = _getEditConfig();
    var editContext = _getEditContext();
    var isStart = properties.get(CONST.COL_CONTROL_TYPE_PROPERTY, CONST.START_PAR_COLUMN_TYPE) == CONST.START_PAR_COLUMN_TYPE;
    var isEnd = properties.get(CONST.COL_CONTROL_TYPE_PROPERTY, CONST.END_PAR_COLUMN_TYPE) == CONST.END_PAR_COLUMN_TYPE;
    var totalColumns = 0;

    if (typeof wcmmode != "undefined") {
        if (isStart && editConfig) {
            var layout = properties.get(CONST.COL_CONTROL_LAYOUT_PROPERTY, CONST.COL_CONTROL_DEFAULT_LAYOUT);
            var layoutInfo = layout.split(";");
            if (layoutInfo.length > 1) {
                totalColumns = layoutInfo[0];
            }
            editConfig.getToolbar().add(0, new global.Packages.com.day.cq.wcm.api.components.Toolbar.Separator());
            editConfig.getToolbar().add(0, new global.Packages.com.day.cq.wcm.api.components.Toolbar.Label("Start of " + totalColumns + " Columns"));
            editConfig.setOrderable(false);
        } else if (isEnd && editConfig && editContext) {
            editConfig.getToolbar().clear();
            editConfig.getToolbar().add(new global.Packages.com.day.cq.wcm.api.components.Toolbar.Label("End of Columns"));
            editConfig.setOrderable(false);
            editContext.setContentPath(resource.getPath() + "_fake");
        }
    }
    return {
        start: isStart,
        end: isEnd,
        totalColumns: totalColumns,
        isTouch: AuthoringUtils.isTouch
    };
});
