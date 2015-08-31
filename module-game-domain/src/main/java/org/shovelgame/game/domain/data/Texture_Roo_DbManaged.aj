// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.data;

import java.util.Set;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.shovelgame.game.domain.data.Texture;
import org.shovelgame.game.domain.data.TextureGroup;
import org.shovelgame.game.domain.data.Version;
import org.shovelgame.game.domain.model.MinionModel;

privileged aspect Texture_Roo_DbManaged {
    
    @OneToMany(mappedBy = "texture")
    private Set<MinionModel> Texture.minionModels;
    
    @ManyToOne
    @JoinColumn(name = "texture_group", referencedColumnName = "id", nullable = false)
    private TextureGroup Texture.textureGroup;
    
    @ManyToOne
    @JoinColumn(name = "version", referencedColumnName = "value", nullable = false)
    private Version Texture.version;
    
    public Set<MinionModel> Texture.getMinionModels() {
        return minionModels;
    }
    
    public void Texture.setMinionModels(Set<MinionModel> minionModels) {
        this.minionModels = minionModels;
    }
    
    public TextureGroup Texture.getTextureGroup() {
        return textureGroup;
    }
    
    public void Texture.setTextureGroup(TextureGroup textureGroup) {
        this.textureGroup = textureGroup;
    }
    
    public Version Texture.getVersion() {
        return version;
    }
    
    public void Texture.setVersion(Version version) {
        this.version = version;
    }
    
}
