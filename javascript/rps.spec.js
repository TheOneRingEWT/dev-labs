const { rps } = require('./rps.js');

describe("rps", () => {

    it("should return 'P1 wins' when P1 chooses Rock and P2 chooses Scissors", () => {
        // Arrange
        const p1 = 'Rock'
        const p2 = 'Scissors'

        const expected = 'P1 wins'

        // Act
        const actual = rps(p1, p2)

        // Assert
        expect(actual).toBe(expected)
    })

    it("should return 'P2 wins' when P1 chooses Scissors and P2 chooses Rock", () => {
        // Arrange
        const p1 = 'Scissors'
        const p2 = 'Rock'

        const expected = 'P2 wins'

        // Act
        const actual = rps(p1, p2)

        // Assert
        expect(actual).toBe(expected)
    })

    it("should return 'P1 wins' when P1 chooses Scissors and P2 chooses Paper", () => {
        // Arrange
        const p1 = 'Scissors'
        const p2 = 'Paper'

        const expected = 'P1 wins'

        // Act
        const actual = rps(p1, p2)

        // Assert
        expect(actual).toBe(expected)
    })

    it("should return 'P2 wins' when P1 chooses Paper and P2 chooses Scissors", () => {
        // Arrange
        const p1 = 'Paper'
        const p2 = 'Scissors'

        const expected = 'P2 wins'

        // Act
        const actual = rps(p1, p2)

        // Assert
        expect(actual).toBe(expected)
    })

    it("should return 'P1 wins' when P1 chooses Paper and P2 chooses Rock", () => {
        // Arrange
        const p1 = 'Paper'
        const p2 = 'Rock'

        const expected = 'P1 wins'

        // Act
        const actual = rps(p1, p2)

        // Assert
        expect(actual).toBe(expected)
    })

    it("should return 'P2 wins' when P1 chooses Rock and P2 chooses Paper", () => {
        // Arrange
        const p1 = 'Rock'
        const p2 = 'Paper'

        const expected = 'P2 wins'

        // Act
        const actual = rps(p1, p2)

        // Assert
        expect(actual).toBe(expected)
    })

    it("should return 'Draw' when P1 chooses Rock and P2 chooses Rock", () => {
        // Arrange, Act, Assert
        const p1 = 'Rock'
        const p2 = 'Rock'

        const expected = 'Draw'
        const actual = rps(p1, p2)

        expect(actual).toBe(expected)
    })

    it("should return 'Draw' when P1 chooses Scissors and P2 chooses Scissors", () => {
        // Arrange, Act, Assert
        const p1 = 'Scissors'
        const p2 = 'Scissors'

        const expected = 'Draw'
        const actual = rps(p1, p2)

        expect(actual).toBe(expected)
    })

    it("should return 'Draw' when P1 chooses Paper and P2 chooses Paper", () => {
        // Arrange, Act, Assert
        const p1 = 'Paper'
        const p2 = 'Paper'

        const expected = 'Draw'
        const actual = rps(p1, p2)

        expect(actual).toBe(expected)
    })
})