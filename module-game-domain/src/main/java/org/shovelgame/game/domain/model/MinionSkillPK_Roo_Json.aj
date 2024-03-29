// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.shovelgame.game.domain.model.MinionSkillPK;

privileged aspect MinionSkillPK_Roo_Json {
    
    public String MinionSkillPK.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String MinionSkillPK.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static MinionSkillPK MinionSkillPK.fromJsonToMinionSkillPK(String json) {
        return new JSONDeserializer<MinionSkillPK>()
        .use(null, MinionSkillPK.class).deserialize(json);
    }
    
    public static String MinionSkillPK.toJsonArray(Collection<MinionSkillPK> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String MinionSkillPK.toJsonArray(Collection<MinionSkillPK> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<MinionSkillPK> MinionSkillPK.fromJsonArrayToMinionSkillPKs(String json) {
        return new JSONDeserializer<List<MinionSkillPK>>()
        .use("values", MinionSkillPK.class).deserialize(json);
    }
    
}
