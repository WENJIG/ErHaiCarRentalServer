package data.entity;

public class CarModel {

    private long modelId;
    private String modelName;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"modelId\":")
                .append(modelId);
        sb.append(",\"modelName\":\"")
                .append(modelName).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
