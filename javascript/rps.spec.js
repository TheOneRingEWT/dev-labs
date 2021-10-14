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
})