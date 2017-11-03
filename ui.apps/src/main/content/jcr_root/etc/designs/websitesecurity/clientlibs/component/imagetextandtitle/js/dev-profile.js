

try {
    if (typeof HCL == 'undefined') {
        HCL = {}; // creating namespace
    }
    HCL.SitemapDatacollection = CQ.Ext.extend(CQ.form.CompositeField, {

    /**
     * @private
     * @type CQ.Ext.form.TextField
     */
    hiddenField : null,
    /**
     * @private
     * @type CQ.Ext.form.PathField
     */
    developerName : null,
    /**
     * @private
     * @type CQ.Ext.form.PathField
     */
    developerDesc : null,
    /**
     * @private
     * @type CQ.Ext.form.MultiField
     */
    replaceMulti : null,    
    /**
     * @private
     * @type CQ.Ext.form.CheckBox
     */
    lastMod : null,
    /**
     * @private
     * @type CQ.Ext.form.ComboBox
     */
    changeFreq : null,
    /**
     * @private
     * @type CQ.Ext.form.ComboBox
     */
    priority : null,
    /**
     * @private
     * @type CQ.Ext.form.MultiField
     */
    skillSet : null,    
    /**
     * @private
     * @type CQ.Ext.form.CheckBox
     */
    temporaryDisable : null,

    constructor : function(config) {
        config = config || {};
        var defaults = {
            "border" : true,
            "padding" : 10,
            "style" : "padding:10px 0 0 5px;",
            "layout" : "form",
            "labelWidth" : 200
        };
        config = CQ.Util.applyDefaults(config, defaults);
        HCL.SitemapDatacollection.superclass.constructor
                .call(this, config);
    },

    // overriding CQ.Ext.Component#initComponent
    initComponent : function() {
        HCL.SitemapDatacollection.superclass.initComponent.call(this);

        // Hidden field
        this.hiddenField = new CQ.Ext.form.Hidden({
            name : this.name
        });
        this.add(this.hiddenField);


        this.developerName = new CQ.Ext.form.TextField({
            fieldLabel : "Developer's Name",
            allowBlank: false,
            width : 400,
            listeners : {
                change : {
                    scope : this,
                    fn : this.updateHidden
                },
                dialogclose : {
                    scope : this,
                    fn : this.updateHidden
                }
            }
        });
        this.add(this.developerName);
        this.developerDesc = new CQ.Ext.form.TextArea({
            fieldLabel : "About Developer",
            fieldDescription: "Provide a detail description about developer",
            allowBlank: false,
            width : 400,
            listeners : {
                change : {
                    scope : this,
                    fn : this.updateHidden
                },
                dialogclose : {
                    scope : this,
                    fn : this.updateHidden
                },
            }
        });
        this.add(this.developerDesc);

        this.skillSet = new CQ.form.MultiField({
            fieldLabel : "Add Skills",
            fieldDescription : "Click '+' to add your Skills",
            width : 400,
            fieldConfig: {
                "xtype" : "textfield",
                allowBlank: false,
            },
            listeners: {
                change: {
                    scope:this,
                    fn:this.updateHidden
                }
            }
        });
        this.add(this.skillSet);
    },
    // overriding CQ.form.CompositeField#setValue
    setValue : function(value) {

        var readVal = '';
        var storeVal = '';
        var replaceMultiValues = '';
        var lastModVal = '';
        var changeFreqVal = '';
        var priorityVal = '';
        var temporaryDisableVal = '';
        var skillSetValues = '';        
        if (value) {
            var colValue = value.split('|');
            if (colValue.length > 0) {
                //temporaryDisableVal = colValue[0];
                readVal             = colValue[0];
                storeVal            = colValue[1];
                //replaceMultiValues  = colValue[3];
                //lastModVal          = colValue[4];
                ///changeFreqVal       = colValue[5];
                //priorityVal         = colValue[6];
                skillSetValues = colValue[2];
            }
        }
        this.developerName.setValue(readVal);
        this.developerDesc.setValue(storeVal);
        //this.replaceMulti.setValue(replaceMultiValues.split(','));
        //this.lastMod.setValue(lastModVal);
        //this.changeFreq.setValue(changeFreqVal);
        //this.priority.setValue(priorityVal);
        //this.temporaryDisable.setValue(temporaryDisableVal);
        this.skillSet.setValue(skillSetValues.split(','));    
    },    
    // overriding CQ.form.CompositeField#getValue
    getValue : function() {
        return this.getRawValue();
    },
    getRawValue : function() {

        //var temporaryDisableVal = this.temporaryDisable.getValue() || "";
        var readVal             = this.developerName.getValue() || "";
        var storeVal            = this.developerDesc.getValue() || "";
        //var replaceMultiValues  = this.replaceMulti.getValue() || "";
       // var lastModVal          = this.lastMod.getValue() || "";
        //var changeFreqVal       = this.changeFreq.getValue() || "";
        //var priorityVal         = this.priority.getValue() || "";
        var skillSetValues = this.skillSet.getValue() || "";
       // if (temporaryDisableVal == '')
       //     temporaryDisableVal = " ";
        var value = readVal + "|" + storeVal + "|" + skillSetValues;
        this.hiddenField.setValue(value);
        return value;
    },
    updateHidden : function() {
        this.hiddenField.setValue(this.getValue());
    },
        destroyRichText : function(){
    this.el.dom={};
}
});
    CQ.Ext.reg('devprofile', HCL.SitemapDatacollection);
} catch (e) {
    // suppressing error.
    // error occurs for CQ.form.CompositeField in mobile devices.
}


try {
    if (typeof HCL == 'undefined') {
        HCL = {}; // creating namespace
    }
    HCL.SitemapDatacollectionReplaceMulti = CQ.Ext.extend(CQ.form.CompositeField, {

    /**
     * @private
     * @type CQ.Ext.form.TextField
     */
    hiddenReplaceMultiField : null,
    /**
     * @private
     * @type CQ.Ext.form.TextField
     */
    replaceWhat : null,    
    /**
     * @private
     * @type CQ.Ext.form.TextField
     */
    replaceWith : null,

    constructor : function(config) {
        config = config || {};
        var defaults = {
            "border" : true,
            "padding" : 10,
            "style" : "padding:10px 0 0 5px;",
            "layout" : "form",
        };
        config = CQ.Util.applyDefaults(config, defaults);
        HCL.SitemapDatacollectionReplaceMulti.superclass.constructor
                .call(this, config);
    },

    // overriding CQ.Ext.Component#initComponent
    initComponent : function() {
        HCL.SitemapDatacollectionReplaceMulti.superclass.initComponent.call(this);

        // Hidden field
        this.hiddenReplaceMultiField = new CQ.Ext.form.Hidden({
            name : this.name
        });
        this.add(this.hiddenReplaceMultiField);
        this.replaceWhat = new CQ.Ext.form.TextField({
            fieldLabel : "Skill",
            fieldDescription: "text that we want to be replaced in site map",
            width : 400,
            regex: /^[A-Za-z0-9-_\/\.]+$/,
            regexText: "Only alphanumeric with -, _ and / allowed.",
            listeners : {
                change : {
                    scope : this,
                    fn : this.updateHidden
                }
            }
       });
        this.add(this.replaceWhat);        
        this.replaceWith = new CQ.Ext.form.TextField({
            fieldLabel : "Replace with",
            fieldDescription: "text that we want to be replaced with in site map",
            width : 400,
            regex: /^[A-Za-z0-9-_\/:\.]+$/,
            regexText: "Only alphanumeric with -, _ and / allowed.",
            listeners : {
                change : {
                    scope : this,
                    fn : this.updateHidden
                }
            }
        });
        this.add(this.replaceWith);
    },
    // overriding CQ.form.CompositeField#setValue
    setValue : function(value) {
        var replaceWhatVal = '';
        var replaceWithVal = '';
        if (value) {
            var colValue = value.split('~');
            if (colValue.length > 0) {
                replaceWhatVal      = colValue[0];
                replaceWithVal      = colValue[1];
            }
        }
        this.replaceWhat.setValue(replaceWhatVal);
        this.replaceWith.setValue(replaceWithVal);
    },    
    // overriding CQ.form.CompositeField#getValue
    getValue : function() {
        return this.getRawValue();
    },
    getRawValue : function() {
        var replaceWhatVal      = this.replaceWhat.getValue() || "";
        var replaceWithVal      = this.replaceWith.getValue() || "";
        var value = replaceWhatVal + "~"  + replaceWithVal;
        this.hiddenReplaceMultiField.setValue(value);
        return value;
    },
    updateHidden : function() {
        this.hiddenReplaceMultiField.setValue(this.getValue());
    }
});
    CQ.Ext.reg('projectSet', HCL.SitemapDatacollectionReplaceMulti);
} catch (e) {
    // suppressing error.
    // error occurs for CQ.form.CompositeField in mobile devices.
}



