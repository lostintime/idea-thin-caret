package com.lostintimedev.java.idea;


import com.intellij.openapi.util.JDOMExternalizable;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.DefaultJDOMExternalizer;
import com.intellij.openapi.components.ApplicationComponent;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

public class ThinCaretConfiguration implements ApplicationComponent, JDOMExternalizable {

    @NotNull
    public String getComponentName() {
        return "ThinCaretConfiguration";
    }

    public void initComponent() {
    }

    public void disposeComponent() {
    }

    public void writeExternal(Element element) throws WriteExternalException {
        DefaultJDOMExternalizer.writeExternal(this, element);
    }

    public void readExternal(Element element) throws InvalidDataException {
        DefaultJDOMExternalizer.readExternal(this, element);
    }

    public boolean equals(final Object bj) {
        if (this == bj) {
            return true;
        }
        if (!(bj instanceof ThinCaretConfiguration)) {
            return false;
        }

        final ThinCaretConfiguration thinCaretConfiguration = (ThinCaretConfiguration) bj;

        if (enabled != thinCaretConfiguration.enabled) {
            return false;
        }

        return true;
    }

    public boolean enabled;
}

