package com.lostintimedev.java.idea;


import com.intellij.openapi.components.*;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.util.xmlb.annotations.Property;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
        name = "thinCaretConfiguration",
        storages = {
                @Storage(id = "other", file = StoragePathMacros.APP_CONFIG + "/thinCaret.xml")
        }
)
public class ThinCaretConfiguration implements ApplicationComponent, PersistentStateComponent<ThinCaretConfiguration> {

    @NotNull
    public String getComponentName() {
        return "ThinCaretConfiguration";
    }

    public void initComponent() {
    }

    public void disposeComponent() {
    }

    @Nullable
    @Override
    public ThinCaretConfiguration getState() {
        return this;
    }

    @Override
    public void loadState(ThinCaretConfiguration thinCaretConfiguration) {
        XmlSerializerUtil.copyBean(thinCaretConfiguration, this);
    }

    public boolean equals(final Object bj) {
        if (this == bj) {
            return true;
        }
        if (!(bj instanceof ThinCaretConfiguration)) {
            return false;
        }

        final ThinCaretConfiguration thinCaretConfiguration = (ThinCaretConfiguration) bj;

        return enabled == thinCaretConfiguration.enabled;
    }

    @Property
    public boolean enabled;
}

