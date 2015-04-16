package ru.nottrue.dev;

interface ISearcher {
    /**
     * Обновляет внутренние структуры данных для последующего быстрого поиска
     * @param classNames названия классов в проекте
     * @param modificationDates дата модификации класса в формате мс, прошедших с 1 января 1970 года
     */
    public void refresh(String[] classNames, long[] modificationDates);

    /**
     * Ищет подходящие имена классов
     * Название должно начинаться с start
     * @param start начало имени класса
     * @return массив длины до 12, имена классов, упорядоченный по дате модификации и лексиграфически
     */
    public String[] guess(String start);
}
