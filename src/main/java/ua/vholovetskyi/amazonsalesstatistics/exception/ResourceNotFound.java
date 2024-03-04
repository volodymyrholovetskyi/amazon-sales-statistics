package ua.vholovetskyi.amazonsalesstatistics.exception;

public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(String nameCriteria, String valueCriteria) {
        super("Resource with %s: %s, not found".formatted(nameCriteria, valueCriteria));
    }
}
