#include "List.h"
#include <iostream>

int main() {
    try {
        List list;
        list.loadFromFile("data/inlet.in");
        list.serialize("outlet.out");

        List copy;
        copy.deserialize("outlet.out");

        std::cout << "Успешно выполнено!" << std::endl;
    } catch (const std::exception& e) {
        std::cerr << "Ошибка: " << e.what() << std::endl;
        return 1;
    }
}
