const rps = (p1, p2) => {
    if (p1 === 'Scissors' && p2 === 'Rock' ||
        p1 === 'Paper' && p2 === 'Scissors')
        return 'P2 wins'

    return 'P1 wins'
}

module.exports = { rps }