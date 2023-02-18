package ru.bardinpetr.itmo.lab5.common.serdes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.bardinpetr.itmo.lab5.common.serdes.exceptions.SerDesException;

import java.io.IOException;

/**
 * Abstract class representing java object serializer/deserializer for type {@code T}.
 * Uses Jackson ObjectMapper as backend.
 *
 * @param <T> base class to operate on
 */
public abstract class SerDesService<T> {
    private final ObjectMapper mapper;
    private final Class<? extends T> baseClass;

    /**
     * Initialize ObjectMapper specified getObjectMapper
     * to enable injecting type names into data what allows use of polymorphism.
     *
     * @param baseClass allow deserialization of this class and all its children
     */
    public SerDesService(Class<? extends T> baseClass) {
        this.baseClass = baseClass;

        mapper = getObjectMapper();
//        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Allow to deserialize all classes from .lab5.models and standard java types
//        var modelsPackage = getClass().getPackageName().replaceFirst("\\.\\w+$", "");
//        var validator =
//                BasicPolymorphicTypeValidator
//                        .builder()
//                        .allowIfBaseType(Pattern.compile(".*"))
//                        .allowIfSubType(modelsPackage)
//                        .allowIfSubTypeIsArray()
//                        .allowIfSubType("java")
//                        .build();
//
//        // Include type info as property (works for XML, as well as for JSON)
//        mapper.activateDefaultTyping(
//                validator,
//                ObjectMapper.DefaultTyping.NON_FINAL,
//                JsonTypeInfo.As.WRAPPER_OBJECT
//        );
    }

    /**
     * Override this function with own creation and configuration of {@link ObjectMapper}
     *
     * @return Configured ObjectMapper
     */
    protected abstract ObjectMapper getObjectMapper();

    /**
     * Serialize object to string or throw exception if impossible.
     *
     * @param object input object.
     * @return String
     * @throws SerDesException thrown if serialization failed
     */
    public byte[] serialize(T object) throws SerDesException {
        try {
            return mapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            throw new SerDesException(SerDesException.Type.SERIALIZE, e);
        }
    }

    /**
     * Deserialize string to object or throw exception if impossible.
     * Allows polymorphism for target type.
     *
     * @param data string to deserialize
     * @return deserialized object as {@code objType}
     * @throws SerDesException thrown if deserialization failed
     */
    public T deserialize(byte[] data) throws SerDesException {
        try {
            return mapper.readValue(data, baseClass);
        } catch (IOException e) {
            throw new SerDesException(SerDesException.Type.DESERIALIZE, e);
        }
    }
}
