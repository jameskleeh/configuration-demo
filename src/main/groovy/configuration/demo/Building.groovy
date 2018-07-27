package configuration.demo

class Building {

    final String name
    final String city
    final int floors

    Building(String name, String city, int floors) {
        this.floors = floors
        this.city = city
        this.name = name
    }

    static Builder builder() {
        return new Builder()
    }

    static class Builder {

        String name
        String city
        int floors

        Builder withName(String name) {
            this.name = name
            this
        }

        Builder withFloors(int floors) {
            this.floors = floors
            this
        }

        Builder withCity(String city) {
            this.city = city
            this
        }

        Building build() {
            new Building(name, city, floors)
        }
    }
}
