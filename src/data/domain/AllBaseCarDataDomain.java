package data.domain;

import data.entity.CarBrand;
import data.entity.CarCategory;
import data.entity.CarModel;

import java.util.ArrayList;

public class AllBaseCarDataDomain {

    private ArrayList<BeCutCar> cars;
    private ArrayList<CarBrand> carBrands;
    private ArrayList<CarModel> carModels;
    private ArrayList<CarCategory> carCategories;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"cars\":")
                .append(cars);
        sb.append(",\"carBrands\":")
                .append(carBrands);
        sb.append(",\"carModels\":")
                .append(carModels);
        sb.append(",\"carCategories\":")
                .append(carCategories);
        sb.append('}');
        return sb.toString();
    }

    public ArrayList<BeCutCar> getCars() {
        return cars;
    }

    public void setCars(ArrayList<BeCutCar> cars) {
        this.cars = cars;
    }

    public ArrayList<CarBrand> getCarBrands() {
        return carBrands;
    }

    public void setCarBrands(ArrayList<CarBrand> carBrands) {
        this.carBrands = carBrands;
    }

    public ArrayList<CarModel> getCarModels() {
        return carModels;
    }

    public void setCarModels(ArrayList<CarModel> carModels) {
        this.carModels = carModels;
    }

    public ArrayList<CarCategory> getCarCategories() {
        return carCategories;
    }

    public void setCarCategories(ArrayList<CarCategory> carCategories) {
        this.carCategories = carCategories;
    }
}
