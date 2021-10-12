const rps = require('./rps.js');

describe("rps", () => {

    it("Should return 'hello world'", () => {

        const expected = 'hello world'
        const actual = rps()

        expect(actual).toBe(expected)
    })
})