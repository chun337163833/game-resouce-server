// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.data;

import java.util.Set;
import javax.persistence.OneToMany;
import org.shovelgame.game.domain.data.Texture;
import org.shovelgame.game.domain.data.TextureGroup;

privileged aspect TextureGroup_Roo_DbManaged {
    
    @OneToMany(mappedBy = "textureGroup")
    private Set<Texture> TextureGroup.textures;
    
    public Set<Texture> TextureGroup.getTextures() {
        return textures;
    }
    
    public void TextureGroup.setTextures(Set<Texture> textures) {
        this.textures = textures;
    }
    
}
