package org.drools.guvnor.server.ruleeditor.workitem;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class WorkitemDefinitionElementsManager {
    private static final String WORKITEM_DEFINITION_ELEMENTS_PROPERTIES = "/workitemDefinitionElements.properties";
    private static WorkitemDefinitionElementsManager INSTANCE;

    private final Map<String, String> properties = new LinkedHashMap<String, String>();

    private WorkitemDefinitionElementsManager() throws IOException {
        Properties props = new Properties();
        props.load(this.getClass().getResourceAsStream(WORKITEM_DEFINITION_ELEMENTS_PROPERTIES));

        this.populateProperties(props.entrySet());
    }

    public synchronized static WorkitemDefinitionElementsManager getInstance() throws IOException {
        if (INSTANCE == null) {
            INSTANCE = new WorkitemDefinitionElementsManager();
        }

        return INSTANCE;
    }

    private void populateProperties(Set<Entry<Object, Object>> fileProps) {
        for (Entry<Object, Object> entry : fileProps) {
            //replace key's '_' to ' '
            String key = entry.getKey().toString().replaceAll("_", " ");

            this.properties.put(key, entry.getValue().toString());
        }
    }

    public Set<String> getElementNames() {
        return Collections.unmodifiableSet(this.properties.keySet());
    }

    public String getElementValue(String elementName) {
        return this.properties.get(elementName);
    }

    public Map<String, String> getElements() {
        return properties;
    }
}
